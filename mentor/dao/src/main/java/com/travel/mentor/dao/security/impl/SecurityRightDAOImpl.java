package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.SecurityRightAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.dao.security.SecurityRightDAO;
import com.travel.mentor.domain.security.SecurityRight;
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

@Repository("securityRightDAO")
@Transactional
public class SecurityRightDAOImpl extends AbstractMentorDAO implements SecurityRightDAO {

    @Resource
    private SecurityRightAssembler securityRightAssembler;

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(SecurityRight.FIND_ALL_SECURITY_RIGHTS);
        super.cacheDomainObjects(namedQueries);
    }

    @Override
    public List<SecurityRightDTO> findAllSecurityRights() {
        List<SecurityRight> securityGroupRightList = em.createNamedQuery(SecurityRight.FIND_ALL_SECURITY_RIGHTS).getResultList();
        return securityRightAssembler.assembleToDTOList(securityGroupRightList);
    }

    @Override
    public List<SecurityRightDTO> findSecurityRightsByLikeRightName(String rightName) {
        Assert.notNull(rightName);
        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME);
        query.setParameter("rightname", rightName);
        return securityRightAssembler.assembleToDTOList(query.getResultList());
    }

    @Override
    public List<SecurityRightDTO> findSecurityRightsByLikeRightNameAndType(String rightName, Long typeId) {
        Assert.notNull(rightName);
        Assert.notNull(typeId);
        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME_AND_TYPE);
        query.setParameter("rightname", rightName);
        query.setParameter("securityrighttypeid", typeId);
        return securityRightAssembler.assembleToDTOList(query.getResultList());
    }

    @Override
    public List<SecurityRightDTO> findSecurityRightsByLikeRightNameAndTypes(String rightName, List<Long> typeList) {
        Assert.notNull(rightName);
        Assert.notNull(typeList);
        Long typeId = typeList.get(0);  // @TODO - need to change this to use a list in the future!!!
        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME_AND_TYPE);
        query.setParameter("rightname", rightName);
        query.setParameter("securityrighttypeid", typeId);
        return securityRightAssembler.assembleToDTOList(query.getResultList());
    }

    @Override
    public List<SecurityRightDTO> findSecurityRightsByType(Long typeId) {
        Assert.notNull(typeId);
        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_TYPE);
        query.setParameter("securityrighttypeid", typeId);
        return securityRightAssembler.assembleToDTOList(query.getResultList());
    }

    @Override
    public SecurityRightDTO saveOrUpdate(SecurityRightDTO securityRightDTO) {
        SecurityRight securityRight;
        if (securityRightDTO.getId() == null || em.find(SecurityRight.class, securityRightDTO.getId()) == null) {
            securityRight = securityRightAssembler.assembleToEntityInstance(securityRightDTO);
            securityRight.setCreateDate(new Timestamp(new Date().getTime()));
        } else {
            securityRight = em.find(SecurityRight.class, securityRightDTO.getId());
            securityRightAssembler.deepCopy(securityRightDTO, securityRight);
        }

        securityRight.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(securityRight);

        return securityRightAssembler.assembleToDTOInstance(securityRight);
    }

    @Override
    public void delete(SecurityRightDTO securityRightDTO) {
        SecurityRight securityRight = em.find(SecurityRight.class, securityRightDTO.getId());
        em.getEntityManagerFactory().getCache().evict(securityRight.getClass(), securityRight.getId());
    }

}
