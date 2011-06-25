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
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Repository("userDAO")
@Transactional
public class UserDAOImpl extends AbstractMentorDAO implements UserDAO {

    @Resource
    private UserAssembler userAssembler;

    @Resource
    private SpringSecurityUserAssembler springSecurityUserAssembler;

    @Override
    public UserDetails findByUsername(String username) {
        Query query = em.createNamedQuery(User.FIND_USER_BY_USERNAME);
        query.setParameter("username", username);

        User user = (User) query.getSingleResult();

        return springSecurityUserAssembler.assembleToSpringSecurityUser(user);
    }

    @Override
    public UserDetails authenticate(String username, String password) {
        Query query = em.createNamedQuery(User.FIND_USER_BY_USERNAME_PASSWORD);
        query.setParameter("username", username);
        query.setParameter("password", password);

        User user = (User) query.getSingleResult();

        return springSecurityUserAssembler.assembleToSpringSecurityUser(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = em.createNamedQuery(User.FIND_ALL_USERS).getResultList();
        return userAssembler.assembleToDTOList(userList);
    }

    @Override
    public UserDTO add(UserDTO userDTO) {
        User user = userAssembler.assembleToDomainObject(userDTO);

        User sessionUser = em.find(User.class, userDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        user.setCreateUser( sessionUser.getUsername() );
        user.setUpdateUser( sessionUser.getUsername() );
        user.setCreateDate(new Timestamp(new Date().getTime()));
        user.setUpdateDate(new Timestamp(new Date().getTime()));

        em.persist(user);

        return userAssembler.assembleToDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userAssembler.assembleToDomainObject(userDTO);

        User sessionUser = em.find(User.class, userDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        user.setUpdateUser( sessionUser.getUsername() );
        user.setUpdateDate(new Timestamp(new Date().getTime()));

        em.merge(user);

        return userAssembler.assembleToDTO(user);
    }

    @Override
    public UserDTO find(String username) {
        User user = em.find(User.class, username);
        return userAssembler.assembleToDTO(user);
    }

    @Override
    protected void cacheDomainObjects() {
        logger.debug("cacheUserDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheUserDomainObjects");
        em.createNamedQuery(User.FIND_ALL_USERS).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds UserDAOImpl.cacheUserDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
