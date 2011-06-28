package com.travel.mentor.service.impl;

import com.travel.mentor.dao.UserDAO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource(name="userDAO")
    private UserDAO userDAO;

    @Override
    public UserDetails findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public UserDetails authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    @Override
    public List<UserDTO> findAll() {
        return userDAO.findAll();
    }

    @Override
    public UserDTO add(UserDTO userDTO) {
        return userDAO.add(userDTO);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return userDAO.update(userDTO);
    }

    @Override
    public UserDTO find(String username) {
        return userDAO.find(username);
    }

}
