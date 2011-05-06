package com.nz.travel.mentor.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import net.flitech.BigDecimalFactory;
import net.flitech.ParamChecker;

/**
 * Helper class for credit card surcharge booking fees.
 * @see SurchargeUtilUnitTest
 */
@Component("surchargeUtil")
public class SurchargeUtil {

    /**
     * Gets the surcharge for one passenger for the number of legs on a flight.
     */
    public BigDecimal getSurcharge(BigDecimal legSurcharge, int numberLegs) {
        ParamChecker.checkNotNull("Leg Surcharge", legSurcharge);
        return legSurcharge.multiply(BigDecimalFactory.createBigDecimal(numberLegs)).setScale(BigDecimalFactory.SCALE);
    }
}

