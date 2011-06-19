package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.IslandDAO;
import com.travel.mentor.dao.RegionDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.IslandDTO;
import com.travel.mentor.dao.dto.RegionDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class RegionDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "regionDAO")
    private RegionDAO regionDAO;

    @Resource(name = "islandDAO")
    private IslandDAO islandDAO;

    private void doPostRetrievalAssertsExpectingIslandRecords(List<IslandDTO> islandDTOList) {
        Assert.assertNotNull(islandDTOList);
        Assert.assertTrue(islandDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingRecords(List<RegionDTO> regionDTOList) {
        Assert.assertNotNull(regionDTOList);
        Assert.assertTrue(regionDTOList.size() != 0);
    }

    @Test
    public void testGetAllRegions() {
        List<RegionDTO> regionDTOList = regionDAO.findAllRegions();
        doPostRetrievalAssertsExpectingRecords(regionDTOList);
    }

    @Test
    public void testAddRegion() {
        List<IslandDTO> islandDTOList = islandDAO.findAllIslands();
        doPostRetrievalAssertsExpectingIslandRecords(islandDTOList);
        IslandDTO islandDTO = islandDTOList.get(0); // get the first one.

        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setIslandDTO(islandDTO);
        regionDTO.setRegionalCouncilName("Taranaki Regional Council");
        regionDTO.setAreaInSquareKilometres(new BigDecimal(567.555));
        regionDTO.setDescription("Taranaki");
        regionDTO.setName("Taranaki");
        regionDTO.setPopulation(567333);

        regionDAO.addRegion(regionDTO);
    }

    @Test
    public void testDeleteRegion() {
        List<RegionDTO> regionDTOList = regionDAO.findAllRegions();
        doPostRetrievalAssertsExpectingRecords(regionDTOList);
        RegionDTO regionDTO = regionDTOList.get(0); // get the first one.

        regionDAO.deleteRegion(regionDTO);
    }

    @Test
    public void testUpdateRegion() {
        List<RegionDTO> regionDTOList = regionDAO.findAllRegions();
        doPostRetrievalAssertsExpectingRecords(regionDTOList);
        RegionDTO regionDTO = regionDTOList.get(0); // get the first one.
        regionDTO.setName("update name 2");
        regionDTO.setDescription("update desc 2");

        regionDAO.updateRegion(regionDTO);
    }


}
