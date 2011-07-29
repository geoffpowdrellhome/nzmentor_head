package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDAO {

    UserDetails findByUsername(String username);

    UserDetails authenticate(String username, String password);

    List<UserDTO> findAll();

    UserDTO add(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO find(String username);

    void delete(UserDTO userDTO);

}
