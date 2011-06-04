package com.travel.mentor.dao.type.impl;

import com.travel.mentor.dao.assemble.ReferenceTypeAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.type.BaseReferenceType;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Repository("referenceTypeDAO")
public class ReferenceTypeDAOImpl extends AbstractMentorDAO implements ReferenceTypeDAO {

    @Resource
    private ReferenceTypeAssembler referenceTypeAssembler;

    @Override
    public List<ReferenceTypeDTO> findAllReferenceTypes(String findAllNamedQuery) {
        Assert.notNull(findAllNamedQuery);
        List<BaseReferenceType> accommodationSiteTypeList = em.createNamedQuery(findAllNamedQuery).getResultList();
        return referenceTypeAssembler.assembleToReferenceTypeDTOList(accommodationSiteTypeList);
    }

}
