package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRoleDTO;

import java.util.List;

public interface SecurityRoleDAO {

    List<SecurityRoleDTO> findAllSecurityRoles();

	SecurityRoleDTO saveOrUpdate(SecurityRoleDTO securityRoleDTO);

	void delete(SecurityRoleDTO securityRoleDTO);

    List<SecurityRoleDTO> getSecurityRolesLikeRoleName(String value);

}
