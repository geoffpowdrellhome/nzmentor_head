package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.SecureUserAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.security.SecureUserDAO;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Repository("secureUserDAO")
@Transactional
public class SecureUserDAOImpl extends AbstractMentorDAO implements SecureUserDAO {

    @Resource
    private SecureUserAssembler secureUserAssembler;

    protected void cacheDomainObjects() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SecureUserDTO findByUsername(String username) {
        SecureUser secureUser = em.find(SecureUser.class, username);
        return secureUserAssembler.assembleToDTO(secureUser);
    }

    @Override
    public SecureUserDTO saveOrUpdate(SecureUserDTO secureUserDTO, SecureUserDTO loggedOnUser) {
        SecureUser secureUser = secureUserAssembler.assembleToDomainObject(secureUserDTO);

        if (secureUserDTO.getUsername() == null || em.find(SecureUser.class, secureUserDTO.getUsername()) == null) {
            secureUser.setCreateUser(loggedOnUser.getUsername());
            secureUser.setCreateDate(new Timestamp(new Date().getTime()));
            secureUser.setUpdateUser(loggedOnUser.getUsername());
            secureUser.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(secureUser);
        }
        else {
            SecureUser existingSecureUser = em.find(SecureUser.class, secureUserDTO.getUsername());
            BeanUtils.copyProperties(secureUser, existingSecureUser);
            secureUser.setUpdateUser(loggedOnUser.getUsername());
            secureUser.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(secureUser);
        }

        return secureUserAssembler.assembleToDTO(secureUser);
    }

}
