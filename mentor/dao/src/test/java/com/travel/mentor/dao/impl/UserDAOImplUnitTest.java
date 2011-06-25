package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.UserDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.dao.dto.impl.RoleDTO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "userDAO")
    private UserDAO userDAO;

    private static final String EXISTING_USERNAME_VALUE="donr";
    private static final String EXISTING_PASSWORD_VALUE="mtalford";

    @Test
    public void testFindAll() {
        List<UserDTO> userDTOList = userDAO.findAll();
        assertRecordsReturned(userDTOList);
    }

    @Test
    public void testFind() {
        UserDTO userDTO = userDAO.find(EXISTING_USERNAME_VALUE);
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void testUpdate() {
        UserDTO userDTO = userDAO.find(EXISTING_USERNAME_VALUE);
        userDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));
        userDTO.setFirstname("johny");
        userDTO.setLastname("begood");
        userDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        userDTO = userDAO.update(userDTO);
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void testFindByUsername() {
        UserDetails userDetails = userDAO.findByUsername(EXISTING_USERNAME_VALUE);
        Assert.assertNotNull(userDetails);
    }

    @Test
    public void testAuthenticate() {
        UserDetails userDetails = userDAO.authenticate(EXISTING_USERNAME_VALUE, EXISTING_PASSWORD_VALUE);
        Assert.assertNotNull(userDetails);
    }

    @Test
    public void testAdd() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("gpow");
        userDTO.setPassword("wallaby1");
        userDTO.setFirstname("Greg");
        userDTO.setLastname("Johnson");
        userDTO.setTitle("Mr");
        userDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        userDTO = userDAO.add(userDTO);
        Assert.assertNotNull(userDTO);
    }

}
