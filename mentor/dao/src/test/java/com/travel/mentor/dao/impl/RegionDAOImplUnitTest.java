package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.IslandDAO;
import com.travel.mentor.dao.RegionDAO;
import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

public class RegionDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "regionDAO")
    private RegionDAO regionDAO;

    @Resource(name = "islandDAO")
    private IslandDAO islandDAO;

    private static final Long EXISTING_ID_VALUE=1L;
    private static final String EXISTING_USERNAME_VALUE="donr";

    @Test
    public void testFindAll() {
        List<RegionDTO> regionDTOList = regionDAO.findAll();
        assertRecordsReturned(regionDTOList);
    }

    @Test
    public void testFind() {
        RegionDTO regionDTO = regionDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(regionDTO);
    }

    @Test
    public void testAdd() {
        List<IslandDTO> islandDTOList = islandDAO.findAll();
        assertRecordsReturned(islandDTOList);
        IslandDTO islandDTO = islandDTOList.get(0); // get the first one.

        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setIslandDTO(islandDTO);
        regionDTO.setRegionalCouncilName("Taranaki Regional Council");
        regionDTO.setAreaInSquareKilometres(new BigDecimal(567.555));
        regionDTO.setDescription("Taranaki");
        regionDTO.setName("Taranaki");
        regionDTO.setPopulation(567333);

        regionDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        regionDTO = regionDAO.add(regionDTO);
        Assert.assertNotNull(regionDTO);
    }

    @Test
    public void testDelete() {
        RegionDTO regionDTO = regionDAO.find(EXISTING_ID_VALUE);
        regionDAO.delete(regionDTO);
    }

    @Test
    public void testUpdate() {
        RegionDTO regionDTO = regionDAO.find(EXISTING_ID_VALUE);
        regionDTO.setName("update name 2");
        regionDTO.setDescription("update desc 2");
        regionDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        regionDTO = regionDAO.update(regionDTO);
        Assert.assertNotNull(regionDTO);
    }

}
