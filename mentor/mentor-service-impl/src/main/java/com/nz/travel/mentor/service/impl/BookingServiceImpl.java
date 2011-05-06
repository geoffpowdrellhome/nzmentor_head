package net.flitech.faregate.service.impl;

import static com.nz.travel.mentor.enums.PaymentType.CASH;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.flitech.BigDecimalFactory;
import net.flitech.ParamChecker;
import net.flitech.StringUtil;

import net.flitech.faregate.Traveller;
import net.flitech.faregate.TravellerType;
import net.flitech.faregate.air.Booking;
import net.flitech.faregate.air.Charge;
import net.flitech.faregate.air.Currency;
import net.flitech.faregate.air.Fare;
import net.flitech.faregate.air.FareBasisDetails;
import net.flitech.faregate.air.FarePrice;
import net.flitech.faregate.air.FareSummary;
import net.flitech.faregate.service.BookingService;

/**
 * This class provides some utilities for extracting useful information from a faregate {@link Booking} object. Ideally,
 * these would be instance methods on the {@link Booking} object, but this can't be done unless we move away from
 * auto-generating from the WSDL.
 * @see BookingServiceImplUnitTest
 */
@Component("bookingService")
public class BookingServiceImpl extends FareGateObject implements BookingService {

    // TODO: write for credit or cash price
    public BigDecimal getTotalAmount(Booking booking, Traveller traveller) {
        return findTotalAmountForTravellerType(booking, traveller.getType(), CASH);
    }

    public BigDecimal findTotalAmountForTravellerType(Booking booking, TravellerType type, PaymentType paymentType) {
        BigDecimal result = getTotalAmountForFare(booking.getOutboundFare(), type, paymentType);
        if (booking.isReturnFlight()) {
            result = result.add(getTotalAmountForFare(booking.getInboundFare().getValue(), type, paymentType));
        }
        return result;
    }

    public BigDecimal getTotalAmount(Booking booking, boolean isCredit) {
        if (logger.isDebugEnabled())
            logger.debug("start getTotalAmount: booking=" + LogUtil.getString(booking) + ", isCredit=" + isCredit);
        BigDecimal totalPayable = calculateTotal(booking.getOutboundFare(), booking.getTravellers(), isCredit);
        if (booking.isReturnFlight()) {
            totalPayable = totalPayable.add(calculateTotal(booking.getInboundFare().getValue(), booking.getTravellers(),
                    isCredit));
        }
        if (logger.isDebugEnabled()) logger.debug("end getTotalAmount: totalPayable=" + totalPayable);
        return totalPayable;
    }

    public BigDecimal getTotalTax(Booking booking) {
        BigDecimal totalTax = calculateTotalTax(booking.getOutboundFare(), booking.getTravellers());
        if (booking.isReturnFlight()) {
            totalTax = totalTax.add(calculateTotalTax(booking.getInboundFare().getValue(), booking.getTravellers()));
        }
        if (logger.isDebugEnabled()) logger.debug("end getTotalTax: totalTax=" + totalTax);
        return totalTax;
    }

    public int countInfants(Booking booking) {
        return countTravellersByType(booking, TravellerType.INFANT);
    }

    public int countChildren(Booking booking) {
        return countTravellersByType(booking, TravellerType.CHILD);
    }

    private int countTravellersByType(Booking booking, TravellerType travellerType) {
        if (booking == null || ParamChecker.isNullOrEmpty(booking.getTravellers())) {
            return 0;
        }
        int count = 0;
        for (Traveller traveller : booking.getTravellers()) {
            if (travellerType == traveller.getType()) count++;
        }
        return count;
    }

    public boolean hasInfants(Booking booking) {
        return countInfants(booking) > 0;
    }

    public boolean hasChildren(Booking booking) {
        return countChildren(booking) > 0;
    }

    public String getCurrency(Booking booking) {
        String currency = booking.getOutboundFare().getPrices().get(0).getCashPrice().getCode();
        if (logger.isDebugEnabled()) logger.debug("end getCurrency: currency=" + currency);
        return currency;
    }

    public int getPaxCount(Booking booking) {
        if (logger.isDebugEnabled()) logger.debug("start getPaxCount: booking=" + LogUtil.getString(booking));
        List<Traveller> travellers = booking.getTravellers();
        int paxCount = 0;
        if (ParamChecker.isNullOrEmpty(travellers)) return paxCount;
        for (Traveller traveller : travellers) {
            if (!TravellerType.INFANT.equals(traveller.getType())) {
                paxCount++;
            }
        }
        if (logger.isDebugEnabled()) logger.debug("end getPaxCount: paxCount=" + paxCount);
        return paxCount;
    }

    /** @param paymentType is CASH by default */
    private BigDecimal getTotalAmountForFare(Fare fare, TravellerType travellerType, PaymentType paymentType) {
        if (paymentType == null) paymentType = CASH;
        BigDecimal result = BigDecimalFactory.ZERO;
        ParamChecker.checkNotNull("fare.prices", fare.getPrices());
        for (FarePrice price : fare.getPrices()) {
            if (travellerType.equals(price.getTravellerType())) {
                result = result.add(findPrice(price, paymentType).getAmount());
                break;
            }
        }
        return result;
    }

    private Currency findPrice(FarePrice price, PaymentType paymentType) {
        return paymentType == CASH ? price.getCashPrice() : price.getCreditPrice();
    }

    private BigDecimal calculateTotal(Fare fare, List<Traveller> travellers, boolean isCredit) {
        BigDecimal total = BigDecimalFactory.ZERO;
        if (fare != null) {
            Map<String, FarePrice> prices = new HashMap<String, FarePrice>();
            if (!ParamChecker.isNullOrEmpty(fare.getPrices())) {
                for (FarePrice price : fare.getPrices()) {
                    prices.put(price.getTravellerType().toString(), price);
                }
            }
            for (Traveller traveller : travellers) {
                FarePrice price = prices.get(traveller.getType().toString());
                if (price != null) {
                    if (TravellerType.INFANT != traveller.getType()) {
                        logger.warn("No prices for fare " + toString(fare) + ", type " + traveller.getType());
                    }
                    continue;
                }
                if (price.getCashPrice().getAmount().compareTo(BigDecimalFactory.ZERO) == 0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("calculateTotal() - Skipping " + traveller.getType() + " as cash price is zero");
                    }
                    continue;
                }
                // TODO use PaymentType.CASH or CREDIT
                Currency finalPrice = isCredit ? price.getCreditPrice() : price.getCashPrice();
                if (logger.isDebugEnabled()) {
                    logger.debug("calculateTotal() - Adding " + LogUtil.getString(finalPrice) + " for "
                            + traveller.getType());
                }
                total = total.add(finalPrice.getAmount());
            }
        }
        return total;
    }

    private BigDecimal calculateTotalTax(Fare fare, List<Traveller> travellers) {
        BigDecimal total = BigDecimalFactory.ZERO;
        if (fare != null) {
            Map<String, Currency> taxes = new HashMap<String, Currency>();
            if (!ParamChecker.isNullOrEmpty(fare.getPrices())) {
                for (FarePrice price : fare.getPrices()) {
                    taxes.put(price.getTravellerType().toString(), price.getTax().getValue());
                }
            }
            for (Traveller traveller : travellers) {
                Currency tax = taxes.get(traveller.getType().toString());
                if (tax != null) {
                    if (TravellerType.INFANT != traveller.getType()) {
                        logger.warn("No prices for fare " + toString(fare) + ", type " + traveller.getType());
                    }
                    continue;
                }
                if (tax.getAmount().compareTo(BigDecimalFactory.ZERO) == 0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("calculateTotal() - Skipping " + traveller.getType() + " as cash price is zero");
                    }
                    continue;
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("calculateTotal() - Adding " + LogUtil.getString(tax) + " for " + traveller.getType());
                }
                total = total.add(tax.getAmount());
            }
        }
        return total;
    }

    private String toString(Fare fare) {
        StringBuffer out = new StringBuffer();
        for (FareBasisDetails d : fare.getDetails()) {
            out.append(d.getFareBasis()).append(" ");
        }
        return out.toString();
    }

    public FareSummary createZeroFareSummary(String currencyCode) {
        FareSummary fareSummary = new FareSummary();
        Currency zero = createCurrency(BigDecimalFactory.ZERO, currencyCode);
        fareSummary.setBase(zero);
        fareSummary.getFees().setTotal(zero);
        fareSummary.getTaxes().setTotal(zero);
        return fareSummary;
    }

    public Currency createCurrency(BigDecimal amount, String code) {
        Currency currency = new Currency();
        currency.setAmount(amount);
        currency.setCode(code);
        return currency;
    }

    /** Sums a List of {@link Currency} objects */
    public Currency sum(List<Currency> currencies, String currencyCode) {
        if (logger.isDebugEnabled())
            logger.debug("start sum: currencies=" + LogUtil.getString(currencies) + ", currencyCode="
                    + StringUtil.angle(currencyCode));
        if (ParamChecker.isNullOrEmpty(currencies)) {
            return createCurrency(BigDecimalFactory.ZERO, currencyCode);
        }
        BigDecimal sum = BigDecimalFactory.ZERO;
        for (Currency currency : currencies) {
            if (currency.getCode() == null || !currency.getCode().equals(currencyCode)) {
                throw new IllegalStateException("Attempted to add " + currency.getAmount() + " of "
                        + currency.getCode() + " currency when expecting " + currencyCode);
            }
            sum = sum.add(currency.getAmount());
        }
        Currency result = createCurrency(sum, currencyCode);
        if (logger.isDebugEnabled()) logger.debug("end sum: result=" + LogUtil.getString(result));
        return result;
    }

    // TODO use varargs for the parameters - add(Currency...)
    /** Adds 2 Currencies. */
    public Currency add(Currency c1, Currency c2) {
        ParamChecker.checkNotNull("currency 1", c1);
        ParamChecker.checkNotNull("currency 2", c2);
        List<Currency> currencies = new ArrayList<Currency>();
        currencies.add(c1);
        currencies.add(c2);
        return sum(currencies, c1.getCode());
    }

    /**
     * @param charges a list of {@link Charge}.
     */
    public <T extends Charge> List<T> sortChargesByTypeThenCode(List<T> charges) {
        Collections.sort(charges, new Comparator<T>() {

            public int compare(T c1, T c2) {
                int typeComparison = compareChargeType(c1, c2);
                if (typeComparison == 0) {
                    return compareChargeCode(c1, c2);
                } else {
                    return typeComparison;
                }
            }

            private int compareChargeCode(T c1, T c2) {
                if (c1.getCode() == null) return c2.getCode() == null ? 0 : -1;
                return c1.getCode().compareTo(c2.getCode());
            }

            private int compareChargeType(T c1, T c2) {
                ParamChecker.checkNotNull("charge1.type", c1.getType());
                ParamChecker.checkNotNull("charge2.type", c2.getType());
                return c1.getType().toString().compareTo(c2.getType().toString());
            }
        });
        return charges;
    }

    /**
     * @param sortedCharges a sorted list of {@link Charge}.
     */
    public <T extends Charge> List<T> reduceDuplicateCharges(List<T> sortedCharges) {
        List<T> reducedCharges = new ArrayList<T>();
        T lastCharge = null;
        for (Iterator<T> iterator = sortedCharges.iterator(); iterator.hasNext();) {
            T charge = iterator.next();
            if (isSameTypeAndCode(lastCharge, charge)) {
                lastCharge.setCost(add(charge.getCost(), lastCharge.getCost()));
                iterator.remove();
            } else {
                lastCharge = charge;
                reducedCharges.add(lastCharge);
            }
        }
        return reducedCharges;
    }

    private boolean isSameTypeAndCode(Charge lastCharge, Charge charge) {
        if (lastCharge == null) {
            return false;
        }

        boolean sameType = charge.getType().equals(lastCharge.getType());
        String thisCode = charge.getCode();
        String lastCode = lastCharge.getCode();
        boolean sameCode = thisCode == null ? lastCode == null : thisCode.equals(lastCode);
        return sameType && sameCode;
    }
}
