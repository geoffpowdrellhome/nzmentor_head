package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.ClimateConditionTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.ClimateConditionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("climateConditionTypeService")
@Transactional
public class ClimateConditionTypeServiceImpl extends AbstractReferenceTypeService implements ClimateConditionTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(ClimateConditionType.FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(ClimateConditionType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}

