package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRoleDTO;

import java.util.List;

public interface SecurityRoleDAO {

    List<SecurityRoleDTO> findAllSecurityRoles();

    SecurityRoleDTO find(Long id);

    List<SecurityRoleDTO> findSecurityRolesByLikeRoleName(String roleName);

	SecurityRoleDTO saveOrUpdate(SecurityRoleDTO securityRoleDTO);

	void delete(SecurityRoleDTO securityRoleDTO);

}
