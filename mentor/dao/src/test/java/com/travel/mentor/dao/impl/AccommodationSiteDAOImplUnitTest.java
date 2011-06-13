package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.LocationDTO;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.RegionDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.RoomConfigurationType;
import com.travel.mentor.type.impl.RoomType;
import com.travel.mentor.type.impl.SiteType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class AccommodationSiteDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "accommodationSiteDAO")
    private AccommodationSiteDAO accommodationSiteDAO;

    private void doPostRetrievalAssertsExpectingAccommodationSites(List<AccommodationSiteDTO> accommodationSiteDTOList) {
        Assert.assertNotNull(accommodationSiteDTOList);
        Assert.assertTrue(accommodationSiteDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingReferenceTypes(List<ReferenceTypeDTO> referenceTypeDTOList) {
        Assert.assertNotNull(referenceTypeDTOList);
        Assert.assertTrue(referenceTypeDTOList.size() != 0);
    }

    @Test
    public void testGetAccommodationSite() {
        List<AccommodationSiteDTO> accommodationSiteDTOList = accommodationSiteDAO.findAllAccommodationSites();
        doPostRetrievalAssertsExpectingAccommodationSites(accommodationSiteDTOList);
    }

    @Test
    public void testAddAccommodationSite() {
        List<ReferenceTypeDTO> siteTypeDTOList = referenceTypeDAO.findAllReferenceTypes(SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(siteTypeDTOList);
        ReferenceTypeDTO siteTypeDTO = siteTypeDTOList.get(0); // get the first one.


        List<ReferenceTypeDTO> accommodationSiteTypeDTOList = referenceTypeDAO.findAllReferenceTypes(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(accommodationSiteTypeDTOList);
        ReferenceTypeDTO accommodationSiteTypeDTO = accommodationSiteTypeDTOList.get(0); // get the first one.

        List<ReferenceTypeDTO> roomTypeDTOList = referenceTypeDAO.findAllReferenceTypes(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(roomTypeDTOList);
        ReferenceTypeDTO roomTypeDTO = roomTypeDTOList.get(0); // get the first one.

        List<ReferenceTypeDTO> roomConfigurationTypeDTOList = referenceTypeDAO.findAllReferenceTypes(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(roomConfigurationTypeDTOList);
        ReferenceTypeDTO roomConfigurationTypeDTO = roomConfigurationTypeDTOList.get(0); // get the first one.


        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(1L);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(1L);
        locationDTO.setRegionDTO(regionDTO);
        locationDTO.setName("test location");
        locationDTO.setDescription("test location");



//        public AccommodationSiteDTO(Long _id,
//                                String _name,
//                                String _description,
//                                ReferenceTypeDTO _siteTypeDTO,
//                                ReferenceTypeDTO _accommodationSiteTypeDTO,
//                                ReferenceTypeDTO _roomTypeDTO,
//                                ReferenceTypeDTO _roomConfigurationTypeDTO,
//                LocationDTO _locationDTO) {



        AccommodationSiteDTO accommodationSiteDTO = new AccommodationSiteDTO(null,
                "Golden Circles Queenstown",
                "Golden Circles Queenstown long description",
                siteTypeDTO,
                accommodationSiteTypeDTO,
                roomTypeDTO,
                roomConfigurationTypeDTO,
                locationDTO);
//
        accommodationSiteDAO.addAccommodationSite(accommodationSiteDTO);
    }

    @Test
    public void testDeleteAccommodationSiteType() {
        List<AccommodationSiteDTO> accommodationSiteDTOList = accommodationSiteDAO.findAllAccommodationSites();
        doPostRetrievalAssertsExpectingAccommodationSites(accommodationSiteDTOList);

        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteDTOList.get(0); // get the first one.
        accommodationSiteDAO.deleteAccommodationSite(accommodationSiteDTO);
    }

    @Test
    public void testUpdateAccommodationSiteType() {
        List<AccommodationSiteDTO> accommodationSiteDTOList = accommodationSiteDAO.findAllAccommodationSites();
        doPostRetrievalAssertsExpectingAccommodationSites(accommodationSiteDTOList);

        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteDTOList.get(0); // get the first one.
        accommodationSiteDTO.setName("update2");
        accommodationSiteDTO.setDescription("update2");
        accommodationSiteDAO.updateAccommodationSite(accommodationSiteDTO);
    }

}
