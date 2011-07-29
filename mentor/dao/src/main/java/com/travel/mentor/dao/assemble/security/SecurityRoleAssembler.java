package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import com.travel.mentor.model.security.SecurityRole;

import java.util.List;

public interface SecurityRoleAssembler {

    List<SecurityRoleDTO> assembleToDTOList(List<SecurityRole> securityRoleList);

    SecurityRole assembleToDomainObject(SecurityRoleDTO securityRoleDTO);

    SecurityRoleDTO assembleToDTO(SecurityRole securityRole);

}
