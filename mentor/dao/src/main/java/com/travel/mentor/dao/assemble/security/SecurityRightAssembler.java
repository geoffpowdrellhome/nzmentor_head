package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.domain.security.SecurityRight;

import java.util.List;

public interface SecurityRightAssembler {

    List<SecurityRightDTO> assembleToDTOList(List<SecurityRight> securityRightList);

    SecurityRight assembleToEntityInstance(SecurityRightDTO securityRightDTO);

    SecurityRightDTO assembleToDTOInstance(SecurityRight securityRight);

    SecurityRight deepCopy(SecurityRightDTO securityRightDTO, SecurityRight securityRight);

}
