package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.LocationDAO;
import com.travel.mentor.dao.UserDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.RoomConfigurationType;
import com.travel.mentor.type.impl.RoomType;
import com.travel.mentor.type.impl.SiteType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class AccommodationSiteDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "accommodationSiteDAO")
    private AccommodationSiteDAO accommodationSiteDAO;

    @Resource(name = "locationDAO")
    private LocationDAO locationDAO;

    @Resource(name = "userDAO")
    private UserDAO userDAO;

    private void doPostRetrievalAssertsExpectingAccommodationSites(List<AccommodationSiteDTO> accommodationSiteDTOList) {
        Assert.assertNotNull(accommodationSiteDTOList);
        Assert.assertTrue(accommodationSiteDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingReferenceTypes(List<ReferenceTypeDTO> referenceTypeDTOList) {
        Assert.assertNotNull(referenceTypeDTOList);
        Assert.assertTrue(referenceTypeDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingLocations(List<LocationDTO> locationDTOList) {
        Assert.assertNotNull(locationDTOList);
        Assert.assertTrue(locationDTOList.size() != 0);
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

        List<LocationDTO> locationDTOList = locationDAO.findAllLocations();
        doPostRetrievalAssertsExpectingLocations(locationDTOList);
        LocationDTO locationDTO = locationDTOList.get(0); // get the first one.

        AccommodationSiteDTO accommodationSiteDTO = new AccommodationSiteDTO();
        accommodationSiteDTO.setAccommodationSiteTypeDTO(accommodationSiteTypeDTO);
        accommodationSiteDTO.setDescription("Mercure Queenstown long description");
        accommodationSiteDTO.setName("Mercure Queenstown");
        accommodationSiteDTO.setSiteTypeDTO(siteTypeDTO);
        accommodationSiteDTO.setLatitude(new BigDecimal(166.677));
        accommodationSiteDTO.setLongitude(new BigDecimal(456.666));
        accommodationSiteDTO.setLocationDTO(locationDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        accommodationSiteDTO.getUserSessionCookieDTO().setUserDTO(userDTO);

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
