package com.nz.travel.mentor.util;

import java.math.BigDecimal;

import net.flitech.BigDecimalFactory;

import net.flitech.faregate.air.Currency;

/**
 * Used for determining the GST component of a currency.
 */
public interface GSTCalculator {

    /**
     * @return a BigDecimal corresponding to the GST rate (e.g. AUD will return 10.00)
     */
    BigDecimal getGstRate(String currencyCode);

    /**
     * @return a BigDecimal corresponding to the GST rate (e.g. 110AUD will return 10AUD). This amount will be scaled to
     *         standard {@link BigDecimalFactory#SCALE}.
     * @throws IllegalArgumentException if the currency code is not supported.
     */
    Currency calculateGSTComponent(Currency gstInclusiveCurrency) throws IllegalArgumentException;

    /**
     * @return a BigDecimal equal to what the GST bit would be if we were to add it (e.g. 200AUD will return 20AUD)
     * @throws IllegalArgumentException if the currency code is not supported.
     */
    Currency calculateGSTOnGSTExclusiveAmount(Currency gstExclusiveCurrency) throws IllegalArgumentException;

    /**
     * @return a BigDecimal equal to what the GST bit would be if we were to add it (e.g. 200AUD will return 20AUD) This
     *         amount will be scaled higher than {@link BigDecimalFactory#SCALE} and <b>will</b> require rounding to get
     *         to an even number of cents.
     * @throws IllegalArgumentException if the currency code is not supported.
     */
    Currency calculatePreciseGSTOnGSTExclusiveAmount(Currency gstExclusiveCurrency) throws IllegalArgumentException;

    /**
     * @return a BigDecimal corresponding to the GST rate (e.g. 110AUD will return 10AUD). This amount will be scaled
     *         higher than {@link BigDecimalFactory#SCALE} and <b>will</b> require rounding to get to an even number of
     *         cents.
     * @throws IllegalArgumentException if the currency code is not supported.
     */
    Currency calculatePreciseGSTComponent(Currency gstInclusiveCurrency) throws IllegalArgumentException;
}


