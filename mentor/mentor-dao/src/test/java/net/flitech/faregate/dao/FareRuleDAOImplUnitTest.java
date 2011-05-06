package net.flitech.faregate.dao;

import com.nz.travel.mentor.dao.FareRuleDAO;
import com.nz.travel.mentor.dao.dto.FareRuleDTO;
import junit.framework.Assert;
import net.flitech.faregate.dao.base.FareGateDAOImplTestCase;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by powdrelg
 * Date: 15/12/2010
 * Purpose: Test, the retrieving of the fare rules by criteria
 */
public class FareRuleDAOImplUnitTest extends FareGateDAOImplTestCase {

    protected static final String VALID_VIRGIN_BLUE_AIRLINE_CODE = "DJ";
    protected static final String INVALID_VIRGIN_BLUE_AIRLINE_CODE = "DJ-XX";

    protected static final String VALID_VIRGIN_BLUE_FARE_BASIS_CODE = "BPTDEC05";
    protected static final String INVALID_VIRGIN_BLUE_FARE_BASIS_CODE = "BPTDEC05-XX";

    protected static final String VALID_VIRGIN_BLUE_FARE_CLASS_CODE = "B";
    protected static final String INVALID_VIRGIN_BLUE_FARE_CLASS_CODE = "B-X";

    private static final DateTimeZone faregateDateTimeZone = DateTimeZone.forTimeZone(TimeZone.getDefault());

    @Resource(name = "fareRuleDAO")
    private FareRuleDAO fareRuleDAO;
        
    private void doPostRetrievalAssertsExpectingRules(List<FareRuleDTO> fareRuleDTOList) {
        Assert.assertNotNull(fareRuleDTOList);
       // Assert.assertTrue(fareRuleDTOList.size() != 0);

//        for (FareRuleDTO fareRuleDTO : fareRuleDTOList) {
//            Assert.assertNotNull(fareRuleDTO.getFareRuleItems());
//            Assert.assertTrue(fareRuleDTO.getFareRuleItems().size() != 0);
//
//            for (FareRuleItemDTO fareRuleItemDTO : fareRuleDTO.getFareRuleItems()) {
//                Assert.assertNotNull(fareRuleItemDTO.getFareRuleItemValues());
//                Assert.assertTrue(fareRuleItemDTO.getFareRuleItemValues().size() != 0);
//            }
////        }
    }

    private void doPostRetrievalAssertsExpectingNoRules(List<FareRuleDTO> fareRuleDTOList) {
        Assert.assertNotNull(fareRuleDTOList);
        Assert.assertTrue(fareRuleDTOList.size() == 0);
    }

    private DateTime getJodaDateTime(int dayOfMonth, int monthOfYear, int year) {
        MutableDateTime dt = new MutableDateTime(new Date(), faregateDateTimeZone);

        dt.setDayOfMonth(dayOfMonth);
        dt.setMonthOfYear(monthOfYear);
        dt.setYear(year);

        return dt.toDateTime();
    }

    @Ignore
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testThrowsIllegalArgumentExceptionFindingRulesBy_AirlineOnly() {
        fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                null,
                null,
                null);
    }

    @Ignore
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testThrowsIllegalArgumentExceptionFindingRulesBy_TravelDepartureDateOnly() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        fareRuleDAO.findFareRulesByCriteria(null,
                travelDepartureDate,
                null,
                null);
    }


    @Ignore
    @Test
    public void testSucceedsFindingRulesBy_Airline_DepartureDate27July() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        null,
                        null);

        doPostRetrievalAssertsExpectingRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingRulesBy_Airline_DepartureDate27July_FareBasisCode() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        VALID_VIRGIN_BLUE_FARE_BASIS_CODE,
                        null);

        doPostRetrievalAssertsExpectingRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingRulesBy_Airline_DepartureDate27July_FareBasisCode_ClassCode() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        VALID_VIRGIN_BLUE_FARE_BASIS_CODE,
                        VALID_VIRGIN_BLUE_FARE_CLASS_CODE);

        doPostRetrievalAssertsExpectingRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingRulesBy_Airline_DepartureDate28July_FareBasisCode() {
        DateTime travelDepartureDate = getJodaDateTime(28, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        VALID_VIRGIN_BLUE_FARE_BASIS_CODE,
                        null);

        doPostRetrievalAssertsExpectingRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingRulesBy_Airline_DepartureDate3August_FareBasisCode() {
        DateTime travelDepartureDate = getJodaDateTime(3, 8, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        VALID_VIRGIN_BLUE_FARE_BASIS_CODE,
                        null);

        doPostRetrievalAssertsExpectingRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingRulesBy_Airline_DepartureDate27July_ClassCode() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        null,
                        VALID_VIRGIN_BLUE_FARE_CLASS_CODE);

        doPostRetrievalAssertsExpectingRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingNoRulesBy_InvalidAirline() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(INVALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        null,
                        null);

        doPostRetrievalAssertsExpectingNoRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingNoRulesBy_InvalidFareBasisCode() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        INVALID_VIRGIN_BLUE_FARE_BASIS_CODE,
                        null);

        doPostRetrievalAssertsExpectingNoRules(fareRuleDTOList);
    }

    @Ignore
    @Test
    public void testSucceedsFindingNoRulesBy_InvalidClassCode() {
        DateTime travelDepartureDate = getJodaDateTime(27, 7, 2010);

        List<FareRuleDTO> fareRuleDTOList =
                fareRuleDAO.findFareRulesByCriteria(VALID_VIRGIN_BLUE_AIRLINE_CODE,
                        travelDepartureDate,
                        VALID_VIRGIN_BLUE_FARE_BASIS_CODE,
                        INVALID_VIRGIN_BLUE_FARE_CLASS_CODE);

        doPostRetrievalAssertsExpectingNoRules(fareRuleDTOList);
    }

}

