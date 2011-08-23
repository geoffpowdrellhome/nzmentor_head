package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecureUserDTO;

public interface SecureUserDAO {

    SecureUserDTO findByUsername(String username);

    SecureUserDTO saveOrUpdate(SecureUserDTO secureUserDTO, SecureUserDTO loggedOnUser);

}
