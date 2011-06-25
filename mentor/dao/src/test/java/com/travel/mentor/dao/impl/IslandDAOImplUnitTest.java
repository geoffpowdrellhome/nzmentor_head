package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.IslandDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import org.junit.Test;
import junit.framework.Assert;
import javax.annotation.Resource;
import java.util.List;

public class IslandDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "islandDAO")
    private IslandDAO islandDAO;

    private static final Long EXISTING_ID_VALUE=1L;

    @Test
    public void testFindAll() {
        List<IslandDTO> islandDTOList = islandDAO.findAll();
        assertRecordsReturned(islandDTOList);
    }

    @Test
    public void testFind() {
        IslandDTO islandDTO = islandDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(islandDTO);
    }

}
