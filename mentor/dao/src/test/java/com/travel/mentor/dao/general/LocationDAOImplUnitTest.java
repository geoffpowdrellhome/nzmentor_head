package com.travel.mentor.dao.general;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.dto.general.RegionDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.reference.ReferenceTypeDAO;
import com.travel.mentor.domain.reference.LocationType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class LocationDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "locationDAO")
    private LocationDAO locationDAO;

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "regionDAO")
    private RegionDAO regionDAO;

    private static final Long EXISTING_ID_VALUE=1L;

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
        locationDTO.setLoggedInUser( secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE));

        locationDTO = locationDAO.saveOrUpdate(locationDTO);
        Assert.assertNotNull(locationDTO);
    }

    @Test
    public void testDelete() {
        LocationDTO existingLocationDTO = locationDAO.find(EXISTING_ID_VALUE);
        locationDAO.delete(existingLocationDTO);
    }

    @Test
    public void testUpdate() {
        LocationDTO locationDTO = locationDAO.find(EXISTING_ID_VALUE);
        locationDTO.setName("update location name2");
        locationDTO.setDescription("update location desc 2");
        locationDTO.setLoggedInUser( secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE));

        locationDTO = locationDAO.saveOrUpdate(locationDTO);
        Assert.assertNotNull(locationDTO);
    }

}
