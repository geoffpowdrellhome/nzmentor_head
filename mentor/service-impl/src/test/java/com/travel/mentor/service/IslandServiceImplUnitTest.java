package com.travel.mentor.service;

import com.travel.mentor.base.AbstractSpringServiceImplTestCase;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class IslandServiceImplUnitTest extends AbstractSpringServiceImplTestCase {

    @Resource(name = "islandService")
    protected IslandService islandService;

    @Test
    public void testFindAll() {
        List<IslandDTO> islandDTOList = islandService.findAll();
        assertRecordsReturned(islandDTOList);
    }

    @Test
    public void testFind() {
        IslandDTO islandDTO = islandService.find(EXISTING_ID_VALUE);
    }

}
