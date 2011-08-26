package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.SecureUserAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.security.SecureUserDAO;
import com.travel.mentor.domain.security.SecureUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("secureUserDAO")
@Transactional
public class SecureUserDAOImpl extends AbstractMentorDAO implements SecureUserDAO {

    @Resource
    private SecureUserAssembler secureUserAssembler;

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(SecureUser.FIND_ALL_SECURE_USERS);
        super.cacheDomainObjects(namedQueries);
    }

    @Override
    public List<SecureUserDTO> findAllSecureUsers() {
        List<SecureUser> secureUserList = em.createNamedQuery(SecureUser.FIND_ALL_SECURE_USERS).getResultList();
        return secureUserAssembler.assembleToDTOList(secureUserList);
    }

    @Override
    public SecureUserDTO findByUsername(String username) {
        return secureUserAssembler.assembleToDTOInstance(em.find(SecureUser.class, username));
    }

    @Override
    public SecureUserDTO saveOrUpdate(SecureUserDTO secureUserDTO, SecureUserDTO loggedOnUser) {
        SecureUser secureUser;
        if (secureUserDTO.getUsername() == null ||
                StringUtils.isEmpty(secureUserDTO.getUsername()) ||
                em.find(SecureUser.class, secureUserDTO.getUsername()) == null) {
            secureUser = secureUserAssembler.assembleToEntityInstance(secureUserDTO);
            secureUser.setCreateDate(new Timestamp(new Date().getTime()));
        } else {
            secureUser = em.find(SecureUser.class, secureUserDTO.getUsername());
            secureUserAssembler.deepCopy(secureUserDTO, secureUser);
        }

        secureUser.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(secureUser);

        return secureUserAssembler.assembleToDTOInstance(secureUser);
    }

    @Override
    public void delete(SecureUserDTO secureUserDTO) {
        SecureUser secureUser = em.find(SecureUser.class, secureUserDTO.getUsername());
        em.getEntityManagerFactory().getCache().evict(secureUser.getClass(), secureUser.getUsername());
    }

}
