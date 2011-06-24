package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.UserDAO;
import com.travel.mentor.dao.assemble.SpringSecurityUserAssembler;
import com.travel.mentor.dao.assemble.UserAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.model.impl.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.util.List;


@Repository("userDAO")
@Transactional
public class UserDAOImpl extends AbstractMentorDAO implements UserDAO {

    @Resource
    private UserAssembler userAssembler;

    @Resource
    private SpringSecurityUserAssembler springSecurityUserAssembler;

    @Override
    public UserDetails findUserByUsername(String username) {
        Query query = em.createNamedQuery(User.FIND_USER_BY_USERNAME);
        query.setParameter("username", username);

        User user = (User) query.getSingleResult();

        return springSecurityUserAssembler.assembleToSpringSecurityUser(user);
    }

    @Override
    public UserDetails authenticateUser(String username, String password) {
        Query query = em.createNamedQuery(User.FIND_USER_BY_USERNAME_PASSWORD);
        query.setParameter("username", username);
        query.setParameter("password", password);

        User user = (User) query.getSingleResult();

        return springSecurityUserAssembler.assembleToSpringSecurityUser(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> userList = em.createNamedQuery(User.FIND_ALL_USERS).getResultList();
        return userAssembler.assembleToUserDTOList(userList);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = userAssembler.assembleToUserDomainObject(userDTO);
        em.persist(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = userAssembler.assembleToUserDomainObject(userDTO);
        em.merge(user);
    }

    @Override
    public UserDTO findUser(Long userId) {
        User user = em.find(User.class, userId);
        return userAssembler.assembleToUserDTO(user);
    }

}
