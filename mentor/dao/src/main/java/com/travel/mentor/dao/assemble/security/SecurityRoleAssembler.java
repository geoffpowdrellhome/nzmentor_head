package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import com.travel.mentor.domain.security.SecurityRole;

import java.util.List;

public interface SecurityRoleAssembler {

    List<SecurityRoleDTO> assembleToDTOList(List<SecurityRole> securityRoleList);

    SecurityRole assembleToEntityInstance(SecurityRoleDTO securityRoleDTO);

    SecurityRoleDTO assembleToDTOInstance(SecurityRole securityRole);

    SecurityRole deepCopy(SecurityRoleDTO securityRoleDTO, SecurityRole securityRole);

}
