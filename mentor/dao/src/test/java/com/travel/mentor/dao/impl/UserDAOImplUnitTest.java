package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.ItemDAO;
import com.travel.mentor.dao.UserDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "userDAO")
    private UserDAO userDAO;

    private void doPostRetrievalAssertsExpectingRecords(List<UserDTO> userDTOList) {
        Assert.assertNotNull(userDTOList);
        Assert.assertTrue(userDTOList.size() != 0);
    }

   @Test
    public void testHappy() {
        Assert.assertTrue(true);
    }

//    @Test
//    public void testGetAllUsers() {
//        List<UserDTO> userDTOList = userDAO.findAllUsers();
//        doPostRetrievalAssertsExpectingRecords(userDTOList);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        List<UserDTO> userDTOList = userDAO.findAllUsers();
//        doPostRetrievalAssertsExpectingRecords(userDTOList);
//        UserDTO userDTO = userDTOList.get(0); // get the first one.
//        userDTO.setFirstname("johny");
//        userDTO.setLastname("begood");
//        userDAO.updateUser(userDTO);
//    }
//
//    @Test
//    public void testFindUser() {
//        List<UserDTO> userDTOList = userDAO.findAllUsers();
//        doPostRetrievalAssertsExpectingRecords(userDTOList);
//
//        UserDTO userDTO = userDAO.findUser(userDTOList.get(0).getId());
//        Assert.assertNotNull(userDTO);
//    }


}
