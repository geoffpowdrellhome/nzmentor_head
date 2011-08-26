package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRoleDTO;

import java.util.List;

public interface SecurityRoleDAO {

    List<SecurityRoleDTO> findAllSecurityRoles();

    List<SecurityRoleDTO> findSecurityRolesByLikeRoleName(String value);

	SecurityRoleDTO saveOrUpdate(SecurityRoleDTO securityRoleDTO);

	void delete(SecurityRoleDTO securityRoleDTO);

}
