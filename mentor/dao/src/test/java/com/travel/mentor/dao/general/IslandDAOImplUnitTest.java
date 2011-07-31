package com.travel.mentor.dao.general;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.general.IslandDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class IslandDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

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
