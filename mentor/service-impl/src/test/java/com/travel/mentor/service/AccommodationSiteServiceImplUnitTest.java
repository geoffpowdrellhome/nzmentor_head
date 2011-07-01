package com.travel.mentor.service;

import com.travel.mentor.base.AbstractSpringServiceImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.service.type.AccommodationSiteTypeService;
import com.travel.mentor.service.type.SiteTypeService;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class AccommodationSiteServiceImplUnitTest extends AbstractSpringServiceImplTestCase {

    @Resource(name = "accommodationSiteService")
    protected AccommodationSiteService accommodationSiteService;

    @Resource(name = "siteTypeService")
    protected SiteTypeService siteTypeService;

    @Resource(name = "accommodationSiteTypeService")
    protected AccommodationSiteTypeService accommodationSiteTypeService;

    @Resource(name = "locationService")
    protected LocationService locationService;

    @Test
    public void testFindAll() {
        List<AccommodationSiteDTO> accommodationSiteDTOList = accommodationSiteService.findAll();
        assertRecordsReturned(accommodationSiteDTOList);
    }

    @Test
    public void testAdd() {
        List<ReferenceTypeDTO> siteTypeDTOList = siteTypeService.findAll();
        assertRecordsReturned(siteTypeDTOList);
        ReferenceTypeDTO siteTypeDTO = siteTypeDTOList.get(0); // get the first one.

        List<ReferenceTypeDTO> accommodationSiteTypeDTOList = accommodationSiteTypeService.findAll();
        assertRecordsReturned(accommodationSiteTypeDTOList);
        ReferenceTypeDTO accommodationSiteTypeDTO = accommodationSiteTypeDTOList.get(0); // get the first one.

        List<LocationDTO> locationDTOList = locationService.findAll();
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

        accommodationSiteDTO.getUserSessionCookieDTO().setUserDTO( userService.find(EXISTING_USERNAME_VALUE));

        accommodationSiteDTO = accommodationSiteService.add(accommodationSiteDTO);
        Assert.assertNotNull(accommodationSiteDTO);
    }

    @Test
    public void testUpdate() {
        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteService.find(EXISTING_ID_VALUE);
        accommodationSiteDTO.setName("update2");
        accommodationSiteDTO.setDescription("update2");
        accommodationSiteDTO.getUserSessionCookieDTO().setUserDTO( userService.find(EXISTING_USERNAME_VALUE));

        accommodationSiteDTO = accommodationSiteService.update(accommodationSiteDTO);
        Assert.assertNotNull(accommodationSiteDTO);
    }

    @Test
    public void testDelete() {
        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteService.find(EXISTING_ID_VALUE);
        accommodationSiteService.delete(accommodationSiteDTO);
    }

    @Test
    public void testFind() {
        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteService.find(EXISTING_ID_VALUE);
    }

}
