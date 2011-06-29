package com.travel.mentor.service;

import com.travel.mentor.base.AbstractSpringServiceImplTestCase;
import com.travel.mentor.dao.dto.impl.UserDTO;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.List;

public class UserServiceTest extends AbstractSpringServiceImplTestCase {

    private static final String EXISTING_USERNAME_VALUE="donr";
    private static final String EXISTING_PASSWORD_VALUE="mtalford";

    @Resource(name = "userService")
    private UserService userService;

    @Test
    public void testFindByUsername() {
        UserDetails userDetails = userService.findByUsername(EXISTING_USERNAME_VALUE);
        Assert.assertNotNull(userDetails);
    }

    @Test
    public void testAuthenticate() {
        UserDetails userDetails = userService.authenticate(EXISTING_USERNAME_VALUE, EXISTING_PASSWORD_VALUE);
        Assert.assertNotNull(userDetails);
    }

    @Test
    public void testFindAll() {
        List<UserDTO> userDTOList = userService.findAll();
        assertRecordsReturned(userDTOList);
    }

    @Test
    public void testAdd() {
        UserDTO existingUserDTO = userService.find(EXISTING_USERNAME_VALUE);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("bobmosford");
        userDTO.setFirstname("bob");
        userDTO.setLastname("mosford");
        userDTO.setTitle("Mr");
        userDTO.setPassword("bobby1");
        userDTO.setRoleDTOList(existingUserDTO.getRoleDTOList());
        userDTO.getUserSessionCookieDTO().setUserDTO(existingUserDTO);

        userDTO = userService.add(userDTO);
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void testUpdate() {
        UserDTO existingUserDTO = userService.find(EXISTING_USERNAME_VALUE);

        existingUserDTO.setEnabled(false);
        existingUserDTO.setCredentialsExpired(true);
        existingUserDTO.setAccountLocked(true);
        existingUserDTO.getUserSessionCookieDTO().setUserDTO(existingUserDTO);

        userService.update(existingUserDTO);
    }

    @Test
    public void testFind() {
        UserDTO existingUserDTO = userService.find(EXISTING_USERNAME_VALUE);
        Assert.assertNotNull(existingUserDTO);
    }

}
