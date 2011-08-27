package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.SecurityRightTypeAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;
import com.travel.mentor.dao.security.SecurityRightTypeDAO;
import com.travel.mentor.domain.security.SecurityRightType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("securityRightTypeDAO")
@Transactional
public class SecurityRightTypeDAOImpl extends AbstractMentorDAO implements SecurityRightTypeDAO {

    @Resource
    private SecurityRightTypeAssembler securityRightTypeAssembler;

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(SecurityRightType.FIND_ALL_SECURITY_RIGHT_TYPES_NAMED_QUERY);
        super.cacheDomainObjects(namedQueries);
    }

    @Override
    public List<SecurityRightTypeDTO> findAllSecurityRightTypes() {
        List<SecurityRightType> securityGroupRightList = em.createNamedQuery(SecurityRightType.FIND_ALL_SECURITY_RIGHT_TYPES_NAMED_QUERY).getResultList();
        return securityRightTypeAssembler.assembleToDTOList(securityGroupRightList);
    }

    @Override
    public SecurityRightTypeDTO find(Long id) {
        return securityRightTypeAssembler.assembleToDTOInstance(em.find(SecurityRightType.class, id));
    }

    @Override
    public SecurityRightTypeDTO saveOrUpdate(SecurityRightTypeDTO securityRightTypeDTO) {
        SecurityRightType securityRightType;
        if (securityRightTypeDTO.getId() == null || em.find(SecurityRightType.class, securityRightTypeDTO.getId()) == null) {
            securityRightType = securityRightTypeAssembler.assembleToEntityInstance(securityRightTypeDTO);
            securityRightType.setCreateUser( secureUserAssembler.assembleToEntityInstance(securityRightTypeDTO.getLoggedInUser()) );
            securityRightType.setCreateDate(new Timestamp(new Date().getTime()));
        }
        else {
            securityRightType = em.find(SecurityRightType.class, securityRightTypeDTO.getId());
            securityRightTypeAssembler.deepCopy(securityRightTypeDTO, securityRightType);
        }

        securityRightType.setUpdateUser( secureUserAssembler.assembleToEntityInstance(securityRightTypeDTO.getLoggedInUser()) );
        securityRightType.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(securityRightType);

        return securityRightTypeAssembler.assembleToDTOInstance(securityRightType);
    }

    @Override
    public void delete(SecurityRightTypeDTO securityRightTypeDTO) {
        SecurityRightType securityRightType = em.find(SecurityRightType.class, securityRightTypeDTO.getId());
        em.getEntityManagerFactory().getCache().evict(securityRightType.getClass(), securityRightType.getId());
    }

}
