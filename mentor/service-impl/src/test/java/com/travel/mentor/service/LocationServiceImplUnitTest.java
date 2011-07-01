package com.travel.mentor.service;

import com.travel.mentor.base.AbstractSpringServiceImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.service.type.LocationTypeService;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class LocationServiceImplUnitTest extends AbstractSpringServiceImplTestCase {

    @Resource(name = "locationService")
    protected LocationService locationService;

    @Resource(name = "locationTypeService")
    protected LocationTypeService locationTypeService;

    @Resource(name = "regionService")
    protected RegionService regionService;

    @Test
    public void testFindAll() {
        List<LocationDTO> locationDTOList = locationService.findAll();
        assertRecordsReturned(locationDTOList);
    }

    @Test
    public void testAdd() {
        List<ReferenceTypeDTO> locationTypeDTOList = locationTypeService.findAll();
        assertRecordsReturned(locationTypeDTOList);
        ReferenceTypeDTO locationTypeDTO = locationTypeDTOList.get(0); // get the first one.

        RegionDTO regionDTO = regionService.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(regionDTO);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocationTypeDTO(locationTypeDTO);
        locationDTO.setRegionDTO(regionDTO);
        locationDTO.setDescription("The Wanaka CBD");
        locationDTO.setName("Wanaka");
        locationDTO.setLatitude(new BigDecimal(103.55));
        locationDTO.setLongitude(new BigDecimal(245.55));
        locationDTO.getUserSessionCookieDTO().setUserDTO(userService.find(EXISTING_USERNAME_VALUE));

        locationDTO = locationService.add(locationDTO);
        Assert.assertNotNull(locationDTO);
    }

    @Test
    public void testUpdate() {
        LocationDTO locationDTO = locationService.find(EXISTING_ID_VALUE);
        locationDTO.setName("update2");
        locationDTO.setDescription("update2");
        locationDTO.getUserSessionCookieDTO().setUserDTO( userService.find(EXISTING_USERNAME_VALUE));

        locationDTO = locationService.update(locationDTO);
        Assert.assertNotNull(locationDTO);
    }

    @Test
    public void testDelete() {
        LocationDTO locationDTO = locationService.find(EXISTING_ID_VALUE);
        locationService.delete(locationDTO);
    }

    @Test
    public void testFind() {
        LocationDTO locationDTO = locationService.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(locationDTO);
    }

}