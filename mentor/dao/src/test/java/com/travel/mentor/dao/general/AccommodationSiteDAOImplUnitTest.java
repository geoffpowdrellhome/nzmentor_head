package com.travel.mentor.dao.general;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.domain.reference.AccommodationSiteType;
import com.travel.mentor.domain.reference.RoomConfigurationType;
import com.travel.mentor.domain.reference.RoomType;
import com.travel.mentor.domain.reference.SiteType;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class AccommodationSiteDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "accommodationSiteDAO")
    private AccommodationSiteDAO accommodationSiteDAO;

    @Resource(name = "locationDAO")
    private LocationDAO locationDAO;

    @Test
    public void testFindAll() {
        List<AccommodationSiteDTO> accommodationSiteDTOList = accommodationSiteDAO.findAll();
        assertRecordsReturned(accommodationSiteDTOList);
    }

    @Test
    public void testFind() {
        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(accommodationSiteDTO);
    }

    @Test
    public void testAdd() {
        List<ReferenceTypeDTO> siteTypeDTOList = referenceTypeDAO.findAll(SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        assertRecordsReturned(siteTypeDTOList);
        ReferenceTypeDTO siteTypeDTO = siteTypeDTOList.get(0); // get the first one.

        List<ReferenceTypeDTO> accommodationSiteTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        assertRecordsReturned(accommodationSiteTypeDTOList);
        ReferenceTypeDTO accommodationSiteTypeDTO = accommodationSiteTypeDTOList.get(0); // get the first one.

        List<LocationDTO> locationDTOList = locationDAO.findAll();
        assertRecordsReturned(locationDTOList);
        LocationDTO locationDTO = locationDTOList.get(0); // get the first one.

        AccommodationSiteDTO accommodationSiteDTO = new AccommodationSiteDTO();
        accommodationSiteDTO.setAccommodationSiteTypeDTO(accommodationSiteTypeDTO);
        accommodationSiteDTO.setDescription("Mercure Queenstown long description");
        accommodationSiteDTO.setName("Mercure Queenstown");
        accommodationSiteDTO.setSiteTypeDTO(siteTypeDTO);
        accommodationSiteDTO.setLatitude(new BigDecimal(166.677));
        accommodationSiteDTO.setLongitude(new BigDecimal(456.666));
        accommodationSiteDTO.setLocationDTO(locationDTO);
        accommodationSiteDTO.setLoggedInUser( securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        accommodationSiteDTO = accommodationSiteDAO.saveOrUpdate(accommodationSiteDTO);
        Assert.assertNotNull(accommodationSiteDTO);
    }

    @Test
    public void testDelete() {
        AccommodationSiteDTO existingAccommodationSiteDTO = accommodationSiteDAO.find(1L);

        AccommodationSiteDTO addAccommodationSiteDTO = new AccommodationSiteDTO();
        BeanUtils.copyProperties(existingAccommodationSiteDTO, addAccommodationSiteDTO);

        addAccommodationSiteDTO.setId(null);
        addAccommodationSiteDTO.setDescription("temp-add");
        addAccommodationSiteDTO.setName("temp-add");
        addAccommodationSiteDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        List<ReferenceTypeDTO> roomTypeDTOList = referenceTypeDAO.findAll(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
        assertRecordsReturned(roomTypeDTOList);
        addAccommodationSiteDTO.setRoomTypeDTO( roomTypeDTOList.get(0) );

        List<ReferenceTypeDTO> roomConfigurationTypeDTOList = referenceTypeDAO.findAll(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
        assertRecordsReturned(roomConfigurationTypeDTOList);
        addAccommodationSiteDTO.setRoomConfigurationTypeDTO( roomConfigurationTypeDTOList.get(0) );

        List<ReferenceTypeDTO> accommodationSiteTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        assertRecordsReturned(accommodationSiteTypeDTOList);
        addAccommodationSiteDTO.setAccommodationSiteTypeDTO( accommodationSiteTypeDTOList.get(0) );


        addAccommodationSiteDTO = accommodationSiteDAO.saveOrUpdate(addAccommodationSiteDTO);

        accommodationSiteDAO.delete(addAccommodationSiteDTO);
    }

    @Test
    public void testUpdate() {
        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteDAO.find(1L);
        accommodationSiteDTO.setName("update2");
        accommodationSiteDTO.setDescription("update2");
        accommodationSiteDTO.setLoggedInUser( securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        accommodationSiteDTO = accommodationSiteDAO.saveOrUpdate(accommodationSiteDTO);
        Assert.assertNotNull(accommodationSiteDTO);
    }

}
