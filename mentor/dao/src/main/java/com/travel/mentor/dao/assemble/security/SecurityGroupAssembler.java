package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.model.security.SecurityGroup;

import java.util.List;

public interface SecurityGroupAssembler {

    List<SecurityGroupDTO> assembleToDTOList(List<SecurityGroup> securityGroupList);

    SecurityGroup assembleToDomainObject(SecurityGroupDTO securityGroupDTO);

    SecurityGroupDTO assembleToDTO(SecurityGroup securityGroup);

}
