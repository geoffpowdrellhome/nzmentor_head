package com.travel.mentor.dao.assemble.security;

import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.security.SecureUser;

import java.util.List;

public interface SecureUserAssembler {

    List<SecureUserDTO> assembleToDTOList(List<SecureUser> secureUserList);

    SecureUser assembleToDomainObject(SecureUserDTO secureUserDTO);

    SecureUserDTO assembleToDTO(SecureUser secureUser);

}
