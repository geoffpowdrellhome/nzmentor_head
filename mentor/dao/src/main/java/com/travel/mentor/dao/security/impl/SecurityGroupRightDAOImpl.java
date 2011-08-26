package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.SecurityGroupRightAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;
import com.travel.mentor.dao.security.SecurityGroupRightDAO;
import com.travel.mentor.domain.security.SecurityGroupRight;
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

@Repository("securityGroupRightDAO")
@Transactional
public class SecurityGroupRightDAOImpl extends AbstractMentorDAO implements SecurityGroupRightDAO {

    @Resource
    private SecurityGroupRightAssembler securityGroupRightAssembler;

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS);
        super.cacheDomainObjects(namedQueries);
    }

    @Override
    public SecurityGroupRightDTO find(Long id) {
        return securityGroupRightAssembler.assembleToDTOInstance(em.find(SecurityGroupRight.class, id));
    }

    @Override
    public List<SecurityGroupRightDTO> findAllSecurityGroupRights() {
        List<SecurityGroupRight> securityGroupRightList = em.createNamedQuery(SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS).getResultList();
        return securityGroupRightAssembler.assembleToDTOList(securityGroupRightList);
    }

    @Override
    public List<SecurityGroupRightDTO> findSecurityGroupRightsBySecurityGroup(SecurityGroupDTO securityGroupDTO) {
        Assert.notNull(securityGroupDTO);
        Query query = em.createNamedQuery(SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS_BY_SECURITY_GROUP);
        query.setParameter("securitygroupid", securityGroupDTO.getId());
        return securityGroupRightAssembler.assembleToDTOList(query.getResultList());
    }

    @Override
    public SecurityGroupRightDTO saveOrUpdate(SecurityGroupRightDTO securityGroupRightDTO) {
        SecurityGroupRight securityGroupRight;
        if (securityGroupRightDTO.getId() == null || em.find(SecurityGroupRight.class, securityGroupRightDTO.getId()) == null) {
            securityGroupRight = securityGroupRightAssembler.assembleToEntityInstance(securityGroupRightDTO);
            securityGroupRight.setCreateDate(new Timestamp(new Date().getTime()));
        } else {
            securityGroupRight = em.find(SecurityGroupRight.class, securityGroupRightDTO.getId());
            securityGroupRightAssembler.deepCopy(securityGroupRightDTO, securityGroupRight);
        }

        securityGroupRight.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(securityGroupRight);

        return securityGroupRightAssembler.assembleToDTOInstance(securityGroupRight);
    }

    @Override
    public void delete(SecurityGroupRightDTO securityGroupRightDTO) {
        SecurityGroupRight securityGroupRight = em.find(SecurityGroupRight.class, securityGroupRightDTO.getId());
        em.getEntityManagerFactory().getCache().evict(securityGroupRight.getClass(), securityGroupRight.getId());
    }

}
