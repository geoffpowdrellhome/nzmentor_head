package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.domain.security.SecurityGroup;

import java.util.List;

public interface SecurityGroupAssembler {

    List<SecurityGroupDTO> assembleToDTOList(List<SecurityGroup> securityGroupList);

    SecurityGroup assembleToEntityInstance(SecurityGroupDTO securityGroupDTO);

    SecurityGroupDTO assembleToDTOInstance(SecurityGroup securityGroup);

    SecurityGroup deepCopy(SecurityGroupDTO securityGroupDTO, SecurityGroup securityGroup);

}
