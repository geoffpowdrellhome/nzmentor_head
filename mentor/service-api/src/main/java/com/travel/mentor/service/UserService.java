package com.travel.mentor.service;

import com.travel.mentor.dao.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDetails findByUsername(String username);

    UserDetails authenticate(String username, String password);

    List<UserDTO> findAll();

    UserDTO add(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO find(String username);

}
