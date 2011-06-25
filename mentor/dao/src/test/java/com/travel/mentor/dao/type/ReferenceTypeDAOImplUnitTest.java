package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.*;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ReferenceTypeDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Test
    public void testGetAccommodationSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testAddAccommodationSiteType() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("Dive-Hotel", "Dive-Hotel", AccommodationSiteType.class);
        referenceTypeDAO.add(referenceTypeDTO);
    }

    @Test
    public void testDeleteAccommodationSiteType() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDAO.delete(referenceTypeDTO);
    }

    @Test
    public void testUpdateAccommodationSiteType() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDTO.setName("update2");
        referenceTypeDTO.setDescription("update2");
        referenceTypeDAO.update(referenceTypeDTO);
    }


    @Test
    public void testGetActivitySiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetClimateConditionTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ClimateConditionType.FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testAddClimateConditionType() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("Windy", "Windy", ClimateConditionType.class);
        referenceTypeDAO.add(referenceTypeDTO);
    }

    @Test
    public void testDeleteClimateConditionType() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ClimateConditionType.FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDAO.delete(referenceTypeDTO);
    }

    @Test
    public void testUpdateClimateConditionType() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ClimateConditionType.FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDTO.setName("update2");
        referenceTypeDTO.setDescription("update2");
        referenceTypeDAO.update(referenceTypeDTO);
    }


    @Test
    public void testGetClimateWindfactorTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ClimateWindfactorType.FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetClothingTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ClothingType.FIND_ALL_CLOTHING_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetEventTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(EventType.FIND_ALL_EVENT_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetFootwearTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(FootwearType.FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetHeadwearTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(HeadwearType.FIND_ALL_HEADWEAR_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetItemTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetLocationTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(LocationType.FIND_ALL_LOCATION_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetPopularityRankingTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(PopularityRankingType.FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetRoomTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetRoomConfigurationTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetSupplierTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(SupplierType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetTransferSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(TransferSiteType.FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

    @Test
    public void testGetVehicleHireSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(VehicleHireSiteType.FIND_ALL_VEHICLE_SITE_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(referenceTypeDTOList);
    }

}
