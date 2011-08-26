package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecureUserDTO;

import java.util.List;

public interface SecureUserDAO {

    List<SecureUserDTO> findAllSecureUsers();

    SecureUserDTO findByUsername(String username);

    SecureUserDTO saveOrUpdate(SecureUserDTO secureUserDTO, SecureUserDTO loggedOnUser);

    void delete(SecureUserDTO secureUserDTO);

}
