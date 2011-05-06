package com.nz.travel.mentor.util;

import java.math.BigDecimal;

import net.flitech.faregate.air.Booking;
import net.flitech.faregate.air.Enquiry;

/**
 * Calculates fees of all descriptions.
 */
public interface FeeCalculator {

    /**
     * A type of fee that pertains to credit card service fees that must be applied per person, per leg.
     */
    String CREDIT_CARD_FEE_PER_PERSON_PER_LEG = "CCSF pp pl";

    /** Returns the type of fee that this object calculates. */
    String getType();

    /** Returns the airline that this object calculates for. */
    String getAirlineName();

    /** Calculates the fee associated with the enquiry. */
    BigDecimal calculateFee(Enquiry enquiry);

    BigDecimal calculateFee(Booking booking);
}
