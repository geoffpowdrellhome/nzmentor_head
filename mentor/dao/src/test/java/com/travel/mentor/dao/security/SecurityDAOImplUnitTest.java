package com.travel.mentor.dao.security;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;
import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import org.junit.Test;

import javax.annotation.Resource;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class SecurityDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "securityDAO")
    private SecurityDAO securityDAO;

    protected static final String EXISTING_GUEST_USERNAME_VALUE="guest";
    protected static final String SECURITY_RIGHTS_LIKE_RIGHT_NAME = "Administration";
    protected static final String SECURITY_GROUPS_LIKE_NAME = "Customers";
    protected static final String SECURITY_ROLES_LIKE_NAME = "REGIONS";

    protected static final Long EXISTING_SECURITY_RIGHTS_TYPE_ID = 3L;

    @Test
    public void testFindSecureUserByUsername() {
        SecureUserDTO secureUserDTO = securityDAO.findByUsername(EXISTING_USERNAME_VALUE);
        Assert.assertNotNull(secureUserDTO);
    }

    @Test
    public void testAddSecureUser() {
        SecureUserDTO loggedOnUser = securityDAO.findByUsername(EXISTING_USERNAME_VALUE);

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

        List<SecurityRoleDTO> securityRoleDTOList = securityDAO.findAllSecurityRoles();

        List<SecurityRoleDTO> assignedSecurityRoleDTOList = new ArrayList<SecurityRoleDTO>();
        assignedSecurityRoleDTOList.add(securityRoleDTOList.get(2));
        assignedSecurityRoleDTOList.add(securityRoleDTOList.get(3));

        secureUserDTO.setSecurityRoleDTOList(assignedSecurityRoleDTOList);

        secureUserDTO = securityDAO.saveOrUpdate(secureUserDTO, loggedOnUser);

        Assert.assertNotNull(secureUserDTO);
    }

    @Test
    public void testUpdateSecureUser() {
        SecureUserDTO loggedOnUser = securityDAO.findByUsername(EXISTING_USERNAME_VALUE);

        SecureUserDTO existingGuestUser = securityDAO.findByUsername(EXISTING_GUEST_USERNAME_VALUE);
        existingGuestUser.setEmail("michaeljfox@gmail.com");

        List<SecurityRoleDTO> securityRoleDTOList = securityDAO.findAllSecurityRoles();

        List<SecurityRoleDTO> assignedSecurityRoleDTOList = new ArrayList<SecurityRoleDTO>();
        assignedSecurityRoleDTOList.add(securityRoleDTOList.get(1));

        existingGuestUser = securityDAO.saveOrUpdate(existingGuestUser, loggedOnUser);

        Assert.assertNotNull(existingGuestUser);
    }

    @Test
    public void testFindAllSecurityRoles() {
        List<SecurityRoleDTO> securityRoleDTOList = securityDAO.findAllSecurityRoles();
        Assert.assertNotNull(securityRoleDTOList);
        Assert.assertTrue(securityRoleDTOList.size() != 0);
    }

    @Test
    public void testAddSecurityRole() {
        SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
        securityRoleDTO.setDescription("Suppliers User role with rights that granted only view of data");
        securityRoleDTO.setRolename("ROLE_SUPPLIERS_ONLY_VIEW");
        securityRoleDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        securityRoleDTO = securityDAO.saveOrUpdate(securityRoleDTO);

        Assert.assertNotNull(securityRoleDTO);
    }

    @Test
    public void testUpdateSecurityRole() {
        List<SecurityRoleDTO> securityRoleDTOList = securityDAO.findAllSecurityRoles();
        SecurityRoleDTO securityRoleDTO = securityRoleDTOList.get(3);
        securityRoleDTO.setDescription("changed role description");
        securityRoleDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        securityRoleDTO = securityDAO.saveOrUpdate(securityRoleDTO);

        Assert.assertNotNull(securityRoleDTO);
    }

    @Test
    public void testDeleteSecurityRole() {
        SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
        securityRoleDTO.setDescription("Suppliers User role with rights that granted only view of data");
        securityRoleDTO.setRolename("ROLE_SUPPLIERS_ONLY_VIEW");
        securityRoleDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        securityRoleDTO = securityDAO.saveOrUpdate(securityRoleDTO);

        Assert.assertNotNull(securityRoleDTO);
    }

    @Test
    public void testFindAllSecurityGroups() {
        List<SecurityGroupDTO> securityGroupDTOList = securityDAO.findAllSecurityGroups();
        Assert.assertNotNull(securityGroupDTOList);
        Assert.assertTrue(securityGroupDTOList.size() != 0);
    }

    @Test
    public void testAddSecurityGroup() {
        SecurityGroupDTO securityGroupDTO = new SecurityGroupDTO();
        securityGroupDTO.setName("Regions_New");
        securityGroupDTO.setDescription("Regions - Allow create new regions");
        securityGroupDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        securityGroupDTO = securityDAO.saveOrUpdate(securityGroupDTO);

        Assert.assertNotNull(securityGroupDTO);
    }

    @Test
    public void testUpdateSecurityGroup() {
        List<SecurityGroupDTO> securityGroupDTOList = securityDAO.findAllSecurityGroups();
        SecurityGroupDTO securityGroupDTO = securityGroupDTOList.get(3);
        securityGroupDTO.setDescription("changed group description");
        securityGroupDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        securityGroupDTO = securityDAO.saveOrUpdate(securityGroupDTO);

        Assert.assertNotNull(securityGroupDTO);
    }

    @Test
    public void testDeleteSecurityGroup() {
        SecurityGroupDTO securityGroupDTO = new SecurityGroupDTO();
        securityGroupDTO.setName("security group new");
        securityGroupDTO.setDescription("security group new--");
        securityGroupDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        securityGroupDTO = securityDAO.saveOrUpdate(securityGroupDTO);


        securityDAO.delete(securityGroupDTO);
    }

    @Test
    public void testDeleteSecurityRight() {
        SecurityRightDTO addSecurityRightDTO = new SecurityRightDTO();
        addSecurityRightDTO.setName("button_RegionDialog_btnHelp");
        addSecurityRightDTO.setDescription("button_RegionDialog_btnHelp");
        addSecurityRightDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        List<SecurityRightTypeDTO> securityRightTypeDTOList = securityDAO.findAllSecurityRightTypes();
        addSecurityRightDTO.setSecurityRightTypeDTO( securityRightTypeDTOList.get(0));

        addSecurityRightDTO = securityDAO.saveOrUpdate(addSecurityRightDTO);

        securityDAO.delete(addSecurityRightDTO);
    }

    @Test
    public void testAddSecurityRight() {
        SecurityRightDTO securityRightDTO = new SecurityRightDTO();
        securityRightDTO.setName("button_RegionDialog_btnHelp");
        securityRightDTO.setDescription("button_RegionDialog_btnHelp");
        securityRightDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        List<SecurityRightTypeDTO> securityRightTypeDTOList = securityDAO.findAllSecurityRightTypes();
        securityRightDTO.setSecurityRightTypeDTO( securityRightTypeDTOList.get(0));

        securityRightDTO = securityDAO.saveOrUpdate(securityRightDTO);

        Assert.assertNotNull(securityRightDTO);
    }

    @Test
    public void testUpdateSecurityRight() {
        List<SecurityRightDTO> SecurityRightDTOList = securityDAO.findAllSecurityRights();
        SecurityRightDTO securityRightDTO = SecurityRightDTOList.get(3);
        securityRightDTO.setDescription("changed group description");
        securityRightDTO.setLoggedInUser(securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        securityRightDTO = securityDAO.saveOrUpdate(securityRightDTO);

        Assert.assertNotNull(securityRightDTO);
    }

    @Test
    public void testFindSecurityRightsByType() {
        List<SecurityRightDTO> securityRightDTOList = securityDAO.findSecurityRightsByType(EXISTING_SECURITY_RIGHTS_TYPE_ID);
        Assert.assertNotNull(securityRightDTOList);
        Assert.assertTrue(securityRightDTOList.size() != 0);
    }

    @Test
    public void testFindAllSecurityRights() {
        List<SecurityRightDTO> securityRightDTOList = securityDAO.findAllSecurityRights();
        Assert.assertNotNull(securityRightDTOList);
        Assert.assertTrue(securityRightDTOList.size() != 0);
    }

    @Test
    public void testFindAllSecurityRightTypes() {
        List<SecurityRightTypeDTO> securityRightTypeDTOList = securityDAO.findAllSecurityRightTypes();
        Assert.assertNotNull(securityRightTypeDTOList);
        Assert.assertTrue(securityRightTypeDTOList.size() != 0);
    }

    @Test
    public void testFindSecurityRightTypeById() {
        SecurityRightTypeDTO securityRightTypeDTO = securityDAO.getSecurityRightTypeById(EXISTING_SECURITY_RIGHTS_TYPE_ID);
        Assert.assertNotNull(securityRightTypeDTO);
    }

    @Test
    public void testFindAllSecurityRightsLikeRightName() {
        List<SecurityRightDTO> securityRightDTOList = securityDAO.getSecurityRightsLikeRightName(SECURITY_RIGHTS_LIKE_RIGHT_NAME);
        Assert.assertNotNull(securityRightDTOList);
        Assert.assertTrue(securityRightDTOList.size() != 0);
    }

    @Test
    public void testFindAllSecurityRightsLikeRightNameAndType() {
        List<SecurityRightDTO> securityRightDTOList = securityDAO.getSecurityRightsLikeRightNameAndType(SECURITY_RIGHTS_LIKE_RIGHT_NAME, EXISTING_SECURITY_RIGHTS_TYPE_ID);
        Assert.assertNotNull(securityRightDTOList);
        Assert.assertTrue(securityRightDTOList.size() != 0);
    }

    @Test
    public void testFindAllSecurityGroupsLikeGroupName() {
        List<SecurityGroupDTO> securityGroupDTOList = securityDAO.getSecurityGroupsLikeGroupName(SECURITY_GROUPS_LIKE_NAME);
        Assert.assertNotNull(securityGroupDTOList);
        Assert.assertTrue(securityGroupDTOList.size() != 0);
    }

    @Test
    public void testFindAllSecurityRolesLikeRoleName() {
        List<SecurityRoleDTO> securityRoleDTOList = securityDAO.getSecurityRolesLikeRoleName(SECURITY_ROLES_LIKE_NAME);
        Assert.assertNotNull(securityRoleDTOList);
        Assert.assertTrue(securityRoleDTOList.size() != 0);
    }

    @Test
    public void testFindAllSecurityGroupRightsBySecurityGroup() {
        List<SecurityGroupDTO> securityGroupDTOList = securityDAO.findAllSecurityGroups();

        List<SecurityGroupRightDTO> SecurityGroupRightDTOList = securityDAO.getSecurityGroupRightsBySecurityGroup(securityGroupDTOList.get(0));
        Assert.assertNotNull(SecurityGroupRightDTOList);
        Assert.assertTrue(SecurityGroupRightDTOList.size() != 0);
    }

    @Test
    public void testFindAllSecurityRightsLikeRightNameAndTypes() {
        List<Long> types = new ArrayList<Long>();
        types.add(EXISTING_SECURITY_RIGHTS_TYPE_ID);

        List<SecurityRightDTO> securityRightDTOList = securityDAO.getSecurityRightsLikeRightNameAndTypes(SECURITY_RIGHTS_LIKE_RIGHT_NAME, types);
        Assert.assertNotNull(securityRightDTOList);
        Assert.assertTrue(securityRightDTOList.size() != 0);
    }

}