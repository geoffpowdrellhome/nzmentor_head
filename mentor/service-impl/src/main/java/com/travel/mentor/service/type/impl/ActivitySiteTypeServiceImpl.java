package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.ActivitySiteTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.ActivitySiteType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("activitySiteTypeService")
@Transactional
public class ActivitySiteTypeServiceImpl extends AbstractReferenceTypeService implements ActivitySiteTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(ActivitySiteType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}

