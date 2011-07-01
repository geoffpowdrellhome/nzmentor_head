package com.travel.mentor.service;

import com.travel.mentor.base.AbstractSpringServiceImplTestCase;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class RegionServiceImplUnitTest extends AbstractSpringServiceImplTestCase {

    @Resource(name = "regionService")
    protected RegionService regionService;

    @Resource(name = "islandService")
    protected IslandService islandService;

    @Test
    public void testFindAll() {
        List<RegionDTO> regionDTOList = regionService.findAll();
        assertRecordsReturned(regionDTOList);
    }

    @Test
    public void testAdd() {
        List<IslandDTO> islandDTOList = islandService.findAll();
        assertRecordsReturned(islandDTOList);
        IslandDTO islandDTO = islandDTOList.get(0); // get the first one.

        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setIslandDTO(islandDTO);
        regionDTO.setRegionalCouncilName("Taranaki Regional Council");
        regionDTO.setAreaInSquareKilometres(new BigDecimal(567.555));
        regionDTO.setDescription("Taranaki");
        regionDTO.setName("Taranaki");
        regionDTO.setPopulation(567333);
        regionDTO.getUserSessionCookieDTO().setUserDTO(userService.find(EXISTING_USERNAME_VALUE));

        regionDTO = regionService.add(regionDTO);
        Assert.assertNotNull(regionDTO);
    }

    @Test
    public void testUpdate() {
        RegionDTO regionDTO = regionService.find(EXISTING_ID_VALUE);
        regionDTO.setName("update name 2");
        regionDTO.setDescription("update desc 2");
        regionDTO.getUserSessionCookieDTO().setUserDTO( userService.find(EXISTING_USERNAME_VALUE));

        regionDTO = regionService.update(regionDTO);
        Assert.assertNotNull(regionDTO);
    }

    @Test
    public void testDelete() {
        RegionDTO regionDTO = regionService.find(EXISTING_ID_VALUE);
        regionService.delete(regionDTO);
    }

    @Test
    public void testFind() {
        RegionDTO regionDTO =  regionService.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(regionDTO);
    }

}
