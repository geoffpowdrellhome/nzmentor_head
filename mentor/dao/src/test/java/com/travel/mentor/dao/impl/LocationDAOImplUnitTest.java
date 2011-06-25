package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.LocationDAO;
import com.travel.mentor.dao.RegionDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.type.impl.LocationType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class LocationDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "locationDAO")
    private LocationDAO locationDAO;

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "regionDAO")
    private RegionDAO regionDAO;

    private static final Long EXISTING_ID_VALUE=1L;
    private static final String EXISTING_USERNAME_VALUE="donr";

    @Test
    public void testFindAll() {
        List<LocationDTO> locationDTOList = locationDAO.findAll();
        assertRecordsReturned(locationDTOList);
    }

    @Test
    public void testFind() {
        LocationDTO locationDTO = locationDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(locationDTO);
    }

    @Test
    public void testAdd() {
        List<ReferenceTypeDTO> locationTypeDTOList = referenceTypeDAO.findAll(LocationType.FIND_ALL_LOCATION_TYPES_NAMED_QUERY);
        assertRecordsReturned(locationTypeDTOList);
        ReferenceTypeDTO locationTypeDTO = locationTypeDTOList.get(0); // get the first one.

        RegionDTO regionDTO = regionDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(regionDTO);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocationTypeDTO(locationTypeDTO);
        locationDTO.setRegionDTO(regionDTO);
        locationDTO.setDescription("The Wanaka CBD");
        locationDTO.setName("Wanaka");
        locationDTO.setLatitude(new BigDecimal(103.55));
        locationDTO.setLongitude(new BigDecimal(245.55));

        locationDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        locationDAO.add(locationDTO);
    }

    @Test
    public void testDelete() {
        LocationDTO locationDTO = locationDAO.find(EXISTING_ID_VALUE);
        locationDAO.delete(locationDTO);
    }

    @Test
    public void testUpdate() {
        LocationDTO locationDTO = locationDAO.find(EXISTING_ID_VALUE);
        locationDTO.setName("update location name2");
        locationDTO.setDescription("update location desc 2");
        locationDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));
        locationDAO.update(locationDTO);
    }

}
