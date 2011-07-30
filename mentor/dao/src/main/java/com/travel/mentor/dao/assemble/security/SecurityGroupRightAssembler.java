package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;
import com.travel.mentor.domain.security.SecurityGroupRight;

import java.util.List;

public interface SecurityGroupRightAssembler {

    List<SecurityGroupRightDTO> assembleToDTOList(List<SecurityGroupRight> securityGroupRightList);

    SecurityGroupRight assembleToDomainObject(SecurityGroupRightDTO securityGroupRightDTO);

    SecurityGroupRightDTO assembleToDTO(SecurityGroupRight securityGroupRight);

}
