package com.nz.travel.mentor.util.impl;

import com.nz.travel.mentor.model.Constants;
import net.flitech.faregate.impl.CreditCardFeeCalculatorFactory;
import net.flitech.faregate.test.base.FareGateTestCase;
import org.jmock.Mock;

import net.flitech.AssertThrows;
import net.flitech.ClassPropertiesUtil;
import net.flitech.AssertThrows.Block;

import net.flitech.faregate.service.LocationGuide;
import com.nz.travel.mentor.util.FeeCalculator;

/**
 * Tests for {@link CreditCardFeeCalculatorFactory}.
 * @see CreditCardFeeCalculatorFactory
 */
public class CreditCardFeeCalculatorFactoryUnitTest extends FareGateTestCase {

    private CreditCardFeeCalculatorFactory factory;
    private Mock mockLocationGuide;
    private LocationGuide locationGuide;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockLocationGuide = mock(LocationGuide.class);
        locationGuide = (LocationGuide) mockLocationGuide.proxy();
        factory = new CreditCardFeeCalculatorFactory(locationGuide);
    }

    public void testClassProperties() {
        ClassPropertiesUtil.checkClassPublic(CreditCardFeeCalculatorFactory.class);
        new CreditCardFeeCalculatorFactory();
        new CreditCardFeeCalculatorFactory(null);
    }

    public void testGetVirginInstance() {
        FeeCalculator calculator = factory.getVirginInstance();
        assertEquals(Constants.VIRGIN_NAME_KEY, calculator.getAirlineName());
        assertCreditCardFeeCalculator(calculator);
    }

    private void assertCreditCardFeeCalculator(FeeCalculator calculator) {
        assertEquals(FeeCalculator.CREDIT_CARD_FEE_PER_PERSON_PER_LEG, calculator.getType());
        assertEquals(true, calculator instanceof PropertyFileCreditCardFeeCalculator);
    }

    public void testGetJetstarInstance() {
        FeeCalculator calculator = factory.getJetstarInstance();
        assertEquals(Constants.JETSTAR_NAME_KEY, calculator.getAirlineName());
        assertCreditCardFeeCalculator(calculator);
    }

    public void testCreateInstance() {
        assertEquals(factory.getJetstarInstance(), factory.createInstance(Constants.JETSTAR_CODE));
        assertEquals(factory.getVirginInstance(), factory.createInstance(Constants.VIRGIN_CODE));
        assertFalse(factory.getVirginInstance().equals(factory.getJetstarInstance()));
        AssertThrows.assertThrows(IllegalArgumentException.class, new Block() {

            public void execute() throws Throwable {
                factory.createInstance(null);
            }
        });
    }
}
