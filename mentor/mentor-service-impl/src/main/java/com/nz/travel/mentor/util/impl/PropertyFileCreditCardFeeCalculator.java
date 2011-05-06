package com.nz.travel.mentor.util.impl;

import static net.flitech.faregate.ServiceConstants.AUSTRALIAN_DOLLAR;
import static net.flitech.faregate.ServiceConstants.NEW_ZEALAND_DOLLAR;

import java.math.BigDecimal;

import net.flitech.BigDecimalFactory;
import net.flitech.ParamChecker;

import net.flitech.faregate.EnquiryExtractor;
import net.flitech.faregate.air.Booking;
import net.flitech.faregate.air.Enquiry;
import net.flitech.faregate.config.AppConfig;
import net.flitech.faregate.service.LocationGuide;
import com.nz.travel.mentor.util.FeeCalculator;

/**
 * Calculates the credit card fees for a particular enquiry based on properties.
 * @see http://www.virginblue.com.au/bookings/fees/
 */
public class PropertyFileCreditCardFeeCalculator extends FareGateObject implements FeeCalculator {

	private static final String KEY_START = ".credit.card.surcharge.";
	static final String AU = "au";
	static final String NZ = "nz";
	static final String NATION_UNKNOWN = "unknown";
	private static final String DOMESTIC = ".domestic";
	private static final String INTERNATIONAL = ".international";
	protected static final String AIRLINE_KEY_VIRGIN = "virgin";
	protected static final String AIRLINE_KEY_JETSTAR = "jetstar";
	protected static final String AIRLINE_KEY_AIRASIA = "airasia";
	protected static final String AIRLINE_KEY_DOLPHIN = "dolphin";
	private final String airlineKey;
	private final LocationGuide locationGuide;

//    @Resource(name="appConfig")
//	private final AppConfig appConfig;

//    @Resource(name="defaultFaregateAppConfig")
//    protected DefaultFaregateAppConfig defaultFaregateAppConfig;

    protected AppConfig appConfig;


	public PropertyFileCreditCardFeeCalculator(String airlineKey, LocationGuide locationGuide, AppConfig appConfig) {
		this.locationGuide = locationGuide;
		this.appConfig = appConfig;
		if (AIRLINE_KEY_VIRGIN.equals(airlineKey) || AIRLINE_KEY_JETSTAR.equals(airlineKey)
				|| AIRLINE_KEY_AIRASIA.equals(airlineKey) || AIRLINE_KEY_DOLPHIN.equals(airlineKey)) {
			this.airlineKey = airlineKey;
		} else {
			throw new IllegalArgumentException("Unknown airline key " + airlineKey);
		}
	}

	public BigDecimal calculateFee(Booking booking) {
		ParamChecker.checkNotNull("Booking", booking);
		ParamChecker.checkNotNull("Booking.departureAirport", booking.getDepartureAirport());
		return calculateFee(new EnquiryExtractor().extractEnquiry(booking));
	}

	/** {@inheritDoc} */
	public BigDecimal calculateFee(Enquiry enquiry) {
		ParamChecker.checkNotNull("Enquiry", enquiry);
		ParamChecker.checkNotNull("Enquiry.departureAirport", enquiry.getDepartureAirport());
		String creditFeeKey = calculateCreditFeeKey(enquiry);
		String value = appConfig.getProperty(creditFeeKey);
		ParamChecker.checkNotNullOrEmpty("value for " + creditFeeKey, value);
		return BigDecimalFactory.createBigDecimal(value);
	}

	public String getAirlineName() {
		return airlineKey;
	}

	/** {@inheritDoc} */
	public String getType() {
		return CREDIT_CARD_FEE_PER_PERSON_PER_LEG;
	}

	/**
	 * Calculates which country the airport is in; either NZ, or AU if unknown.
	 * @param currencyCode the airport
	 * @return AU or NZ
	 */
	// TODO base the credit card fee on the currency code, not the airport
	String calculateNationality(String currencyCode) {
		if (NEW_ZEALAND_DOLLAR.contains(currencyCode)) return NZ;
		if (AUSTRALIAN_DOLLAR.contains(currencyCode)) return AU;
		return NATION_UNKNOWN;
	}

	/**
	 * Calculates which key to look up in app.properties to find the correct fee for this enquiry.
	 * @param enquiry the enquiry
	 * @return the credit card fee key.
	 */
	private String calculateCreditFeeKey(Enquiry enquiry) {
		return airlineKey + KEY_START + calculateNationality(enquiry.getCurrencyCode()) + calculateIntDom(enquiry);
	}

	/**
	 * Calculates whether a flight is international or domestic.
	 * @return DOMESTIC or INTERNATIONAL
	 */
	private String calculateIntDom(Enquiry enquiry) {
		return locationGuide.isDomestic(enquiry.getDepartureAirport(), enquiry.getArrivalAirport()) ? DOMESTIC
				: INTERNATIONAL;
	}
}
