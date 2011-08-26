package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;
import com.travel.mentor.domain.security.SecurityRightType;

import java.util.List;

public interface SecurityRightTypeAssembler {

    List<SecurityRightTypeDTO> assembleToDTOList(List<SecurityRightType> securityRightTypeList);

    SecurityRightType assembleToEntityInstance(SecurityRightTypeDTO securityRightTypeDTO);

    SecurityRightTypeDTO assembleToDTOInstance(SecurityRightType securityRightType);

    SecurityRightType deepCopy(SecurityRightTypeDTO securityRightTypeDTO, SecurityRightType securityRightType);

}
