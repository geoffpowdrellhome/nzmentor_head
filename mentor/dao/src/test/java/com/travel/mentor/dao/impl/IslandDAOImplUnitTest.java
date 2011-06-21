package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.IslandDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class IslandDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "islandDAO")
    private IslandDAO islandDAO;

    private void doPostRetrievalAssertsExpectingRecords(List<IslandDTO> islandDTOList) {
        Assert.assertNotNull(islandDTOList);
        Assert.assertTrue(islandDTOList.size() != 0);
    }

    @Test
    public void testGetAllIslands() {
        List<IslandDTO> islandDTOList = islandDAO.findAllIslands();
        doPostRetrievalAssertsExpectingRecords(islandDTOList);
    }

}
