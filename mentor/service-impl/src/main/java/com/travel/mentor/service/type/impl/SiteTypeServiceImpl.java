package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.SiteTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.SiteType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("siteTypeService")
@Transactional
public class SiteTypeServiceImpl extends AbstractReferenceTypeService implements SiteTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(SiteType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}

