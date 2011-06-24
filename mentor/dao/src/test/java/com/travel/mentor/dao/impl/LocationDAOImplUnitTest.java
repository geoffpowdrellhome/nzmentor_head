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

    private void doPostRetrievalAssertsExpectingRecords(List<LocationDTO> locationDTOList) {
        Assert.assertNotNull(locationDTOList);
        Assert.assertTrue(locationDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingReferenceTypes(List<ReferenceTypeDTO> referenceTypeDTOList) {
        Assert.assertNotNull(referenceTypeDTOList);
        Assert.assertTrue(referenceTypeDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingRegionRecords(List<RegionDTO> regionDTOList) {
        Assert.assertNotNull(regionDTOList);
        Assert.assertTrue(regionDTOList.size() != 0);
    }

    @Test
    public void testGetAllLocations() {
        List<LocationDTO> locationDTOList = locationDAO.findAllLocations();
        doPostRetrievalAssertsExpectingRecords(locationDTOList);
    }

    @Test
    public void testAddLocation() {
        List<ReferenceTypeDTO> locationTypeDTOList = referenceTypeDAO.findAllReferenceTypes(LocationType.FIND_ALL_LOCATION_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(locationTypeDTOList);
        ReferenceTypeDTO locationTypeDTO = locationTypeDTOList.get(0); // get the first one.

        List<RegionDTO> regionDTOList = regionDAO.findAllRegions();
        doPostRetrievalAssertsExpectingRegionRecords(regionDTOList);
        RegionDTO regionDTO = regionDTOList.get(0); // get the first one.

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocationTypeDTO(locationTypeDTO);
        locationDTO.setRegionDTO(regionDTO);
        locationDTO.setDescription("The Wanaka CBD");
        locationDTO.setName("Wanaka");
        locationDTO.setLatitude(new BigDecimal(103.55));
        locationDTO.setLongitude(new BigDecimal(245.55));

        locationDAO.addLocation(locationDTO);
    }

    @Test
    public void testDeleteLocation() {
        List<LocationDTO> locationDTOList = locationDAO.findAllLocations();
        doPostRetrievalAssertsExpectingRecords(locationDTOList);
        LocationDTO locationDTO = locationDTOList.get(0); // get the first one.

        locationDAO.deleteLocation(locationDTO);
    }

    @Test
    public void testUpdateLocation() {
        List<LocationDTO> locationDTOList = locationDAO.findAllLocations();
        doPostRetrievalAssertsExpectingRecords(locationDTOList);
        LocationDTO locationDTO = locationDTOList.get(0); // get the first one.
        locationDTO.setName("update location name2");
        locationDTO.setDescription("update location desc 2");

        locationDAO.updateLocation(locationDTO);
    }

    @Test
    public void testFindLocation() {
        List<LocationDTO> locationDTOList = locationDAO.findAllLocations();
        doPostRetrievalAssertsExpectingRecords(locationDTOList);

        LocationDTO locationDTO = locationDAO.findLocation(locationDTOList.get(0).getId());
        Assert.assertNotNull(locationDTO);
    }

}
