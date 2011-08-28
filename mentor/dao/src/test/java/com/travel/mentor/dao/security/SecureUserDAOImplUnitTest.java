package com.travel.mentor.dao.security;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class SecureUserDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "secureUserDAO")
    private SecureUserDAO secureUserDAO;

    @Resource(name = "securityRoleDAO")
    private SecurityRoleDAO securityRoleDAO;

    protected static final String EXISTING_GUEST_USERNAME_VALUE="guest";
    protected static final String SECURITY_RIGHTS_LIKE_RIGHT_NAME = "Administration";
    protected static final String SECURITY_GROUPS_LIKE_NAME = "Customers";
    protected static final String SECURITY_ROLES_LIKE_NAME = "REGIONS";

    protected static final Long EXISTING_SECURITY_RIGHTS_TYPE_ID = 3L;

    @Test
    public void testFindAll() {
        List<SecureUserDTO> itemDTOList = secureUserDAO.findAllSecureUsers();
        assertRecordsReturned(itemDTOList);
    }

    @Test
    public void testFindByUsername() {
        SecureUserDTO secureUserDTO = secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE);
        Assert.assertNotNull(secureUserDTO);
    }

    @Test
    public void testFindSecureUsersByLikeUserName() {
        List<SecureUserDTO> secureUserDTOList = secureUserDAO.findSecureUsersByLikeUserName("user");
        assertRecordsReturned(secureUserDTOList);
    }

    @Test
    public void testAddSecureUser() {
        SecureUserDTO loggedOnUser = secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE);

        SecureUserDTO secureUserDTO = new SecureUserDTO();
        secureUserDTO.setAccountNonExpired(true);
        secureUserDTO.setAccountNonLocked(true);
        secureUserDTO.setCredentialsNonExpired(true);
        secureUserDTO.setEmail("michaelbacker@gmail.com");
        secureUserDTO.setEnabled(true);
        secureUserDTO.setFirstname("Michael");
        secureUserDTO.setLastname("Baker");
        secureUserDTO.setLocale("EN_US");
        secureUserDTO.setPassword("wallaby1");
        secureUserDTO.setTitle("Mr");
        secureUserDTO.setUsername("bakerm1");

        List<SecurityRoleDTO> securityRoleDTOList = securityRoleDAO.findAllSecurityRoles();

        List<SecurityRoleDTO> assignedSecurityRoleDTOList = new ArrayList<SecurityRoleDTO>();
        assignedSecurityRoleDTOList.add(securityRoleDTOList.get(2));
        assignedSecurityRoleDTOList.add(securityRoleDTOList.get(3));

        secureUserDTO.setSecurityRoleDTOList(assignedSecurityRoleDTOList);

        secureUserDTO = secureUserDAO.saveOrUpdate(secureUserDTO, loggedOnUser);

        Assert.assertNotNull(secureUserDTO);
    }

    @Test
    public void testUpdateSecureUser() {
        SecureUserDTO loggedOnUser = secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE);

        SecureUserDTO existingGuestUser = secureUserDAO.findByUsername(EXISTING_GUEST_USERNAME_VALUE);
        existingGuestUser.setEmail("michaeljfox@gmail.com");

        List<SecurityRoleDTO> securityRoleDTOList = securityRoleDAO.findAllSecurityRoles();
        List<SecurityRoleDTO> assignedSecurityRoleDTOList = new ArrayList<SecurityRoleDTO>();
        assignedSecurityRoleDTOList.add(securityRoleDTOList.get(1));

        existingGuestUser = secureUserDAO.saveOrUpdate(existingGuestUser, loggedOnUser);

        Assert.assertNotNull(existingGuestUser);
    }

    @Test
    public void testDeleteSecureUser() {
        SecureUserDTO secureUserDTO = secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE);
        secureUserDAO.delete(secureUserDTO);
    }

}
