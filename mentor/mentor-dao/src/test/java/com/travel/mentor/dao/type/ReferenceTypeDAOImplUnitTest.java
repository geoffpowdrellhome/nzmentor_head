package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.type.impl.*;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ReferenceTypeDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    private void doPostRetrievalAssertsExpectingReferenceTypes(List<ReferenceTypeDTO> referenceTypeDTOList) {
        Assert.assertNotNull(referenceTypeDTOList);
        Assert.assertTrue(referenceTypeDTOList.size() != 0);
    }


    @Test
    public void testGetAccommodationSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetActivitySiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetClimateConditionTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(ClimateConditionType.FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetClimateWindfactorTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(ClimateWindfactorType.FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetClothingTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(ClothingType.FIND_ALL_CLOTHING_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetEventTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(EventType.FIND_ALL_EVENT_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetFootwearTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(FootwearType.FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetHeadwearTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(HeadwearType.FIND_ALL_HEADWEAR_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetItemTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetLocationTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(LocationType.FIND_ALL_LOCATION_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetPopularityRankingTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(PopularityRankingType.FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetRoomTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetRoomConfigurationTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetSupplierTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(SupplierType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetTransferSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(TransferSiteType.FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testGetVehicleHireSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(VehicleHireSiteType.FIND_ALL_VEHICLE_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }


}
