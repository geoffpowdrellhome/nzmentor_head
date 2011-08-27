package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.SecurityRoleAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import com.travel.mentor.dao.security.SecurityRoleDAO;
import com.travel.mentor.domain.security.SecurityRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.util.Assert;
import javax.persistence.Query;

@Repository("securityRoleDAO")
@Transactional
public class SecurityRoleDAOImpl extends AbstractMentorDAO implements SecurityRoleDAO {

    @Resource
    private SecurityRoleAssembler securityRoleAssembler;

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(SecurityRole.FIND_ALL_SECURITY_ROLES);
        super.cacheDomainObjects(namedQueries);
    }

    @Override
    public List<SecurityRoleDTO> findAllSecurityRoles() {
        List<SecurityRole> securityRoleList = em.createNamedQuery(SecurityRole.FIND_ALL_SECURITY_ROLES).getResultList();
        return securityRoleAssembler.assembleToDTOList(securityRoleList);
    }

    @Override
    public SecurityRoleDTO find(Long id) {
        return securityRoleAssembler.assembleToDTOInstance(em.find(SecurityRole.class, id));
    }

    @Override
    public List<SecurityRoleDTO> findSecurityRolesByLikeRoleName(String roleName) {
        Assert.notNull(roleName);
        Query query = em.createNamedQuery(SecurityRole.FIND_SECURITY_ROLES_BY_LIKE_ROLE_NAME);
        query.setParameter("rolename", "%" + roleName + "%");
        return securityRoleAssembler.assembleToDTOList(query.getResultList());
    }

    @Override
    public SecurityRoleDTO saveOrUpdate(SecurityRoleDTO securityRoleDTO) {
        SecurityRole securityRole;
        if (securityRoleDTO.getId() == null || em.find(SecurityRole.class, securityRoleDTO.getId()) == null) {
            securityRole = securityRoleAssembler.assembleToEntityInstance(securityRoleDTO);
            securityRole.setCreateUser( secureUserAssembler.assembleToEntityInstance(securityRoleDTO.getLoggedInUser()) );
            securityRole.setCreateDate(new Timestamp(new Date().getTime()));
        }
        else {
            securityRole = em.find(SecurityRole.class, securityRoleDTO.getId());
            securityRoleAssembler.deepCopy(securityRoleDTO, securityRole);
        }

        securityRole.setUpdateUser( secureUserAssembler.assembleToEntityInstance(securityRoleDTO.getLoggedInUser()) );
        securityRole.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(securityRole);

        return securityRoleAssembler.assembleToDTOInstance(securityRole);
    }

    @Override
    public void delete(SecurityRoleDTO securityRoleDTO) {
        SecurityRole securityRole = em.find(SecurityRole.class, securityRoleDTO.getId());
        em.getEntityManagerFactory().getCache().evict(securityRole.getClass(), securityRole.getId());
    }

}
