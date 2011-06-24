package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDAO {

    UserDetails findUserByUsername(String username);

    UserDetails authenticateUser(String username, String password);

    List<UserDTO> findAllUsers();

    void addUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    UserDTO findUser(Long userId);

}
