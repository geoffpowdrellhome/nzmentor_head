package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.RoleDAO;
import com.travel.mentor.dao.assemble.RoleAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.RoleDTO;
import com.travel.mentor.model.impl.Role;
import com.travel.mentor.model.impl.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("roleDAO")
@Transactional
public class RoleDAOImpl extends AbstractMentorDAO implements RoleDAO {

    @Resource
    private RoleAssembler roleAssembler;

    @Override
    protected void cacheDomainObjects() {
        logger.debug("RoleDAOImpl.cacheDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("RoleDAOImpl.cacheDomainObjects()");
        em.createNamedQuery(Role.FIND_ALL_ROLES).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds RoleDAOImpl.cacheDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roleList = em.createNamedQuery(Role.FIND_ALL_ROLES).getResultList();
        return roleAssembler.assembleToDTOList(roleList);
    }

    @Override
    public RoleDTO add(RoleDTO roleDTO) {
        Role role = roleAssembler.assembleToDomainObject(roleDTO);

        User sessionUser = em.find(User.class, roleDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        role.setCreateUser(sessionUser);
        role.setUpdateUser(sessionUser);
        role.setCreateDate(new Timestamp(new Date().getTime()));
        role.setUpdateDate(new Timestamp(new Date().getTime()));

        em.persist(role);

        return roleAssembler.assembleToDTO(role);
    }

    @Override
    public void delete(RoleDTO roleDTO) {
        Role role = em.find(Role.class, roleDTO.getId());
        em.remove(role);
    }

}
