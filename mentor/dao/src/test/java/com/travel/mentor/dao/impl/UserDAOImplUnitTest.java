package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.UserDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.impl.UserDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "userDAO")
    private UserDAO userDAO;

    private static final String EXISTING_USERNAME_VALUE="donr";

    @Test
    public void testFindAll() {
        List<UserDTO> userDTOList = userDAO.findAll();
        doExpectingRecordsAssert(userDTOList);
    }

    @Test
    public void testFind() {
        UserDTO userDTO = userDAO.find(EXISTING_USERNAME_VALUE);
        Assert.assertNotNull(userDTO);
        doExpectingRecordsAssert(userDTO.getRoleDTOList());
    }

    @Test
    public void testUpdate() {
        UserDTO userDTO = userDAO.find(EXISTING_USERNAME_VALUE);
        userDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));
        userDTO.setFirstname("johny");
        userDTO.setLastname("begood");
        userDAO.update(userDTO);
    }

}
