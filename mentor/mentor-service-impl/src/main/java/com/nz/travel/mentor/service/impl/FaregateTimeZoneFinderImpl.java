package net.flitech.faregate.service.impl;

import static net.flitech.ParamChecker.isNullOrEmpty;
import static net.flitech.StringUtil.angle;

import static com.nz.travel.mentor.util.DateUtil.createGMTCalendar;
import static com.nz.travel.mentor.util.DateUtil.dateToString;
import static com.nz.travel.mentor.util.DateUtil.stringToDate;
import static com.nz.travel.mentor.util.LogUtil.getString;

import com.googlecode.ehcache.annotations.Cacheable;

import net.flitech.faregate.FaregateLoginDetails;
import net.flitech.faregate.air.translator.impl.FaregateTranslator;
import org.springframework.stereotype.Component;

import net.flitech.faregate.service.FaregateTimeZoneFinder;
//import net.flitech.faregate.galileo.translator.impl.GalileoTranslator;
//import net.flitech.faregate.galileo.xmlselect.LocalDateTimeFinder;
//import net.flitech.faregate.galileo.xmlselect.model.GalileoLoginDetails;

/**
 * Finds time zone information.
  */
@Component("faregateTimeZoneFinder")
public class FaregateTimeZoneFinderImpl extends FaregateTranslator implements FaregateTimeZoneFinder {


    /** {@inheritDoc} */
    @Override
    @Cacheable(cacheName = "timeZoneOffsets")
    public String findTimeZoneOffset(FaregateLoginDetails loginDetails, String airport) {

//        String sig = "findTimeZoneOffset(" + airport + ") - ";
//        LocalDateTimeCT60Document localDateTimeResponse = localDateTimeFinder.localDateTime(loginDetails,
//                createLocalDateTimeRequest(airport));
//        if (logger.isTraceEnabled()) logger.trace(sig + "localDateTimeResponse=" + getString(localDateTimeResponse));
//        if (!hasLocalDateTime(localDateTimeResponse)) {
//            logger.warn(format("missing local datetime for airport <{0}>", airport));
//            return null;
//        }
//        long gmtTime = findGMTTime();
//        long localTime = toTimeInMillis(localDateTimeResponse);
//        if (logger.isTraceEnabled()) {
//            logger.trace(sig + "gmtTime=" + gmtTime + ", localTime=" + localTime);
//            String gmtString = dateToString(new Date(gmtTime), DATETIME_FORMAT, TIME_ZONE_GMT);
//            String localString = dateToString(new Date(localTime), DATETIME_FORMAT, TIME_ZONE_GMT);
//            logger.trace(sig + "gmtTime=" + gmtString + ", localTime=" + localString);
//        }
//        String offset = createTimezoneOffset(localTime - gmtTime);
//        logger.debug(sig + "returning " + offset);
//        return offset;

        // @@TODO - Implement this as a separate task later.  (geoff)

        return "timezoneOffset";
    }



//    /** {@inheritDoc} */
//    @Override
//    @Cacheable(cacheName = "timeZoneOffsets")
//    public String findTimeZoneOffset(FaregateLoginDetails loginDetails, String airport) {
//
//        String sig = "findTimeZoneOffset(" + airport + ") - ";
//        LocalDateTimeCT60Document localDateTimeResponse = localDateTimeFinder.localDateTime(loginDetails,
//                createLocalDateTimeRequest(airport));
//        if (logger.isTraceEnabled()) logger.trace(sig + "localDateTimeResponse=" + getString(localDateTimeResponse));
//        if (!hasLocalDateTime(localDateTimeResponse)) {
//            logger.warn(format("missing local datetime for airport <{0}>", airport));
//            return null;
//        }
//        long gmtTime = findGMTTime();
//        long localTime = toTimeInMillis(localDateTimeResponse);
//        if (logger.isTraceEnabled()) {
//            logger.trace(sig + "gmtTime=" + gmtTime + ", localTime=" + localTime);
//            String gmtString = dateToString(new Date(gmtTime), DATETIME_FORMAT, TIME_ZONE_GMT);
//            String localString = dateToString(new Date(localTime), DATETIME_FORMAT, TIME_ZONE_GMT);
//            logger.trace(sig + "gmtTime=" + gmtString + ", localTime=" + localString);
//        }
//        String offset = createTimezoneOffset(localTime - gmtTime);
//        logger.debug(sig + "returning " + offset);
//        return offset;
//    }
//
//    private boolean hasLocalDateTime(LocalDateTimeCT60Document response) {
//        return !isNull(response) && !isNull(response.getLocalDateTimeCT60())
//                && !isNull(response.getLocalDateTimeCT60().getLocalDateTime())
//                && !isNullOrEmpty(response.getLocalDateTimeCT60().getLocalDateTime().getDt())
//                && !isNullOrEmpty(response.getLocalDateTimeCT60().getLocalDateTime().getTm());
//    }
//
//    // TODO inject DateUtil and make methods non-static to improve testability
//    /** only override when testing the time zone offset */
//    protected long findGMTTime() {
//        return createGMTCalendar().getTimeInMillis();
//    }
//
//    protected void setLocalDateTimeFinder(LocalDateTimeFinder in) {
//        localDateTimeFinder = in;
//    }
//
//    private LocalDateTimeCT60Document createLocalDateTimeRequest(String airport) {
//        LocalDateTimeCT60Document dateTimeRequest = LocalDateTimeCT60Document.Factory.newInstance();
//        dateTimeRequest.addNewLocalDateTimeCT60().addNewLocalDateTimeMods().setReqCity(airport);
//        return dateTimeRequest;
//    }
//
//    private String createTimezoneOffset(long offset) {
//        if (logger.isTraceEnabled()) logger.trace("start createTimezoneOffset: offset=" + angle(offset));
//        offset = roundToNearestHalfHour(offset);
//        String prefix = offset >= 0 ? "+" : "-";
//        Calendar calendar = createGMTCalendar();
//        calendar.setTimeInMillis(Math.abs(offset)); // does not accept negative time
//        if (logger.isTraceEnabled())
//            logger.trace("createTimezoneOffset: calendar=" + dateToString(calendar.getTime(), DATETIME_FORMAT));
//        String result = prefix + dateToString(calendar.getTime(), "HHmm", TIME_ZONE_GMT);
//        if (logger.isTraceEnabled()) logger.trace("end createTimezoneOffset: offset=" + angle(result));
//        return result;
//    }
//
//    /** rounds to nearest 30 minutes to avoid discrepancies between Galileo time and localhost time. */
//    private long roundToNearestHalfHour(long offset) {
//        return Math.round(offset / 1800000.0D) * 1800000;
//    }
//
//    private long toTimeInMillis(LocalDateTimeCT60Document localDateTimeResponse) {
//        LocalDateTime localDateTime = localDateTimeResponse.getLocalDateTimeCT60().getLocalDateTime();
//        String dateString = localDateTime.getDt().toString() + SPACE + padLeft(4, localDateTime.getTm());
//        if (logger.isTraceEnabled()) logger.trace("toTimeInMillis: dateString=" + angle(dateString));
//        return stringToDate(dateString, "yyyyMMdd HHmm", TIME_ZONE_GMT).getTime();
//    }
}

