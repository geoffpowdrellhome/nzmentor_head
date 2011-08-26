package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.SecurityGroupAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.dao.security.SecurityGroupDAO;
import com.travel.mentor.domain.security.SecurityGroup;
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

@Repository("securityGroupDAO")
@Transactional
public class SecurityGroupDAOImpl extends AbstractMentorDAO implements SecurityGroupDAO {

    @Resource
    private SecurityGroupAssembler securityGroupAssembler;

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(SecurityGroup.FIND_ALL_SECURITY_GROUPS);
        super.cacheDomainObjects(namedQueries);
    }

    @Override
    public SecurityGroupDTO find(Long id) {
        return securityGroupAssembler.assembleToDTOInstance(em.find(SecurityGroup.class, id));
    }

    @Override
    public List<SecurityGroupDTO> findAllSecurityGroups() {
        List<SecurityGroup> securityGroupList = em.createNamedQuery(SecurityGroup.FIND_ALL_SECURITY_GROUPS).getResultList();
        return securityGroupAssembler.assembleToDTOList(securityGroupList);
    }

    @Override
    public List<SecurityGroupDTO> findSecurityGroupsByLikeGroupName(String groupName) {
        Assert.notNull(groupName);
        Query query = em.createNamedQuery(SecurityGroup.FIND_SECURITY_GROUPS_BY_LIKE_GROUP_NAME);
        query.setParameter("groupname", groupName);
        return securityGroupAssembler.assembleToDTOList(query.getResultList());
    }

    @Override
    public SecurityGroupDTO saveOrUpdate(SecurityGroupDTO securityGroupDTO) {
        SecurityGroup securityGroup;
        if (securityGroupDTO.getId() == null || em.find(SecurityGroup.class, securityGroupDTO.getId()) == null) {
            securityGroup = securityGroupAssembler.assembleToEntityInstance(securityGroupDTO);
            securityGroup.setCreateDate(new Timestamp(new Date().getTime()));
        } else {
            securityGroup = em.find(SecurityGroup.class, securityGroupDTO.getId());
            securityGroupAssembler.deepCopy(securityGroupDTO, securityGroup);
        }

        securityGroup.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(securityGroup);

        return securityGroupAssembler.assembleToDTOInstance(securityGroup);
    }

    @Override
    public void delete(SecurityGroupDTO securityGroupDTO) {
        SecurityGroup securityGroup = em.find(SecurityGroup.class, securityGroupDTO.getId());
        em.getEntityManagerFactory().getCache().evict(securityGroup.getClass(), securityGroup.getId());
    }

}
