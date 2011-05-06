package com.nz.travel.mentor.util.impl;

import com.nz.travel.mentor.model.impl.User;
import net.flitech.faregate.FaregateEmulator;
import net.flitech.faregate.FaregateLoginDetails;
import net.flitech.faregate.ValidationException;
import net.flitech.faregate.air.Airline;
import net.flitech.faregate.air.AirlineDetails;
import net.flitech.faregate.air.Booking;
import net.flitech.faregate.air.Enquiry;
import net.flitech.faregate.service.FaregateTimeZoneFinder;
import com.nz.travel.mentor.util.TimeZoneConverter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.nz.travel.mentor.model.Constants.DATETIME_FORMAT;
import static com.nz.travel.mentor.model.Constants.GALILEO_CODE;

@Component("timeZoneConverter")
public class TimeZoneConverterImpl implements TimeZoneConverter {

    @Resource(name = "faregateTimeZoneFinder")
    protected FaregateTimeZoneFinder timeZoneFinder;

    private static final Logger LOGGER = Logger.getLogger(TimeZoneConverterImpl.class);
    private static final String THIS_TIME_SHOULD_HAVE_BEEN_VALIDATED_EARLIER = "). This time should have been validated earlier";
    private static final String COULD_NOT_CONVERT = "Could not convert (";
    private static final String USING_PATTERN = ") using pattern (";
    private static final String GMT = "GMT";

    @Resource(name = "userProviderSettingsDAO")
    private UserProviderSettingsDAO userProviderSettingsDAO;

    @Resource
    private FaregateEmulator faregateEmulator;

    protected FaregateLoginDetails findLoginDetails(User user, Enquiry enquiry) {
        return findLoginDetails(user, findGalileo(enquiry.getAirlines()));
    }

    protected FaregateLoginDetails findLoginDetails(User user, Booking booking) {
        return findLoginDetails(user, nullSafe(booking.getAirline()));
    }

    /**
     * Should already have passed validation.
     */
    protected FaregateLoginDetails findLoginDetails(User user, Airline airline) {
        if (faregateEmulator.isEmulationEnabled(user)) {
            return faregateEmulator.findLoginDetails(user, airline);
        } else {
            return new FaregateLoginDetails(airline.getAgencyUsername(), airline.getAgencyPassword(),
                    airline.getPseudo(), null);
        }
    }

    protected AirlineDetails findGalileo(List<AirlineDetails> airlines) {
        if (airlines == null || airlines.size() == 0) {
            return new AirlineDetails();
        }

        for (AirlineDetails airline : airlines) {
            if (GALILEO_CODE.equals(airline.getAirlineCode())) {
                return airline;
            }
        }

        return new AirlineDetails();
    }

    protected Airline nullSafe(Airline airline) {
        return airline == null ? new Airline() : airline;
    }


    @Override
    public String convertArrivalToDeparture(User user, Booking booking) throws ValidationException {
        return convertTimeZone(booking.getArrivalTime(), timeZoneFinder.findTimeZoneOffset(findLoginDetails(user,
                booking), booking.getArrivalAirport()), timeZoneFinder.findTimeZoneOffset(findLoginDetails(user,
                booking), booking.getDepartureAirport()));
    }

    @Override
    public String convertReturnDepartureToArrival(User user, Booking booking) throws ValidationException {
        return convertTimeZone(booking.getReturnDepartureTime().getValue(), timeZoneFinder.findTimeZoneOffset(findLoginDetails(
                user, booking), booking.getArrivalAirport()), timeZoneFinder.findTimeZoneOffset(findLoginDetails(user,
                booking), booking.getDepartureAirport()));
    }

    @Override
    public String convertReturnArrivalToReturnDeparture(User user, Booking booking) throws ValidationException {
        return convertTimeZone(booking.getReturnArrivalTime().getValue(), timeZoneFinder.findTimeZoneOffset(findLoginDetails(user,
                booking), booking.getDepartureAirport()), timeZoneFinder.findTimeZoneOffset(findLoginDetails(user,
                booking), booking.getArrivalAirport()));
    }

    private String convertTimeZone(String time, String sourceTimeZone, String destTimeZone) throws ValidationException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);
        Date specifiedTime;
        try {
            if (sourceTimeZone != null) formatter.setTimeZone(TimeZone.getTimeZone(GMT + sourceTimeZone));
            specifiedTime = formatter.parse(time);
            if (destTimeZone != null) formatter.setTimeZone(TimeZone.getTimeZone(GMT + destTimeZone));
        } catch (ParseException pe) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(COULD_NOT_CONVERT + time + USING_PATTERN + formatter.toPattern()
                        + THIS_TIME_SHOULD_HAVE_BEEN_VALIDATED_EARLIER, pe);
            }
            throw new ValidationException();
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Converted " + time + " with timezoneOffset " + sourceTimeZone + " to "
                    + formatter.format(specifiedTime) + " with timezoneOffset " + destTimeZone);
        }
        return formatter.format(specifiedTime);
    }
}


