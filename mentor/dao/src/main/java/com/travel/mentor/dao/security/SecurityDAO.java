package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.*;

import java.util.List;

public interface SecurityDAO {

    SecureUserDTO findByUsername(String username);

    /* +++++ Security: Userroles +++++++ */
	//public int getCountAllSecurityUserRoles();

	//public SecUserrole getNewSecUserrole();

	//public void saveOrUpdate(SecUserrole userRole);

    SecureUserDTO saveOrUpdate(SecureUserDTO secureUserDTO, SecureUserDTO loggedOnUser);

	//public void delete(SecUserrole userRole);

    //public List<SecurityRole> getAllUserRoles();

	//public SecUserrole getUserroleByUserAndRole(SecUser user, SecRole role);

//	public List<SecUserrole> getAllUserRoles();
//
//	public SecUserrole getUserroleByUserAndRole(SecUser user, SecRole role);

	//public boolean isUserInRole(SecureUser user, SecurityRole role);

	/* +++++ Security: Roles +++++++ */
	//public int getCountAllSecRoles();

	//public SecurityRole getNewSecurityRole();

	List<SecurityRoleDTO> findAllSecurityRoles();

	SecurityRoleDTO saveOrUpdate(SecurityRoleDTO securityRoleDTO);

	void delete(SecurityRoleDTO securityRoleDTO);

	/* +++++ Security: RoleGroups +++++++ */
	//public int getCountAllSecRolegroups();

	//public SecurityRoleGroup getNewSecurityRoleGroup();

	//public void saveOrUpdate(SecurityRoleGroupDTO securityRoleGroup);

	//public void delete(SecurityRoleGroupDTO securityRoleGroup);

	//public List<SecurityRoleGroup> getAllSecurityRoleGroups();

	//public SecurityRoleGroup getRolegroupByRoleAndGroup(SecurityRole securityRole, SecurityGroup securityGroup);

	//public boolean isGroupInRole(SecurityGroup group, SecurityRole role);

	/* +++++ Security: Groups +++++++ */

	//public int getCountAllSecurityGroups();

	List<SecurityGroupDTO> findAllSecurityGroups();

	//public SecurityGroup getNewSecurityGroup();

	SecurityGroupDTO saveOrUpdate(SecurityGroupDTO securityGroupDTO);

	void delete(SecurityGroupDTO securityGroupDTO);

	/* +++++ Security: Rights +++++++ */
	//public int getCountAllSecRights();

	//public SecurityRight getNewSecurityRight();

	void delete(SecurityRightDTO securityRightDTO);

	SecurityRightDTO saveOrUpdate(SecurityRightDTO securityRightDTO);

	/**
	 * Get all rights. The result can limited by the type.<br>
	 * <br>
	 *
	 * Int | Type <br>
	 * --------------------------<br>
	 * -1 | All (no filter) <br>
	 * 0 | Page <br>
	 * 1 | Menu Category <br>
	 * 2 | Menu Item <br>
	 * 3 | Method <br>
	 * 4 | DomainObject/Property <br>
	 * 5 | Tab <br>
	 * 6 | Components <br>
	 *
	 */
	List<SecurityRightDTO> findSecurityRightsByType(Long type);

	//public List<SecurityRightDTO> getAllSecurityRights(List<Integer> list);

	List<SecurityRightDTO> findAllSecurityRights();

	//public boolean isRightinGroup(SecurityRight securityRight, SecurityGroup securityGroup);

	/* +++++ Security: Grouprights +++++++ */

	//public int getCountAllSecurityGroupRights();

	//public List<SecurityGroupRightDTO> findAllSecurityGroupRights();

	//public SecurityGroupRight getNewSecurityGroupRight();

	//public void delete(SecurityGroupRight securityGroupRight);

	//public void saveOrUpdate(SecurityGroupRight securityGroupRight);

	/* +++++ Security: Security Typs +++++++ */
	List<SecurityRightTypeDTO> findAllSecurityRightTypes();

	SecurityRightTypeDTO getSecurityRightTypeById(Long id);

	/* +++++++++++++++++++++++++++++++++++++++++++ */

	//public SecurityGroupRight getSecurityGroupRightByGroupAndRight(SecurityGroup securityGroup, SecurityRight securityRight);

	List<SecurityRightDTO> getSecurityRightsLikeRightName(String value);

	List<SecurityRightDTO> getSecurityRightsLikeRightNameAndType(String value, Long typeId);

	List<SecurityGroupDTO> getSecurityGroupsLikeGroupName(String value);

	List<SecurityRoleDTO> getSecurityRolesLikeRoleName(String value);

	List<SecurityGroupRightDTO> getSecurityGroupRightsBySecurityGroup(SecurityGroupDTO securityGroupDTO);

	List<SecurityRightDTO> getSecurityRightsLikeRightNameAndTypes(String value, List<Long> list);

}
