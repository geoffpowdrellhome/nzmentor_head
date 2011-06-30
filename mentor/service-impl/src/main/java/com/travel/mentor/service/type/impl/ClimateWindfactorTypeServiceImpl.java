package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.ClimateWindfactorTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.ClimateConditionType;
import com.travel.mentor.type.impl.ClimateWindfactorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("climateWindfactorTypeService")
@Transactional
public class ClimateWindfactorTypeServiceImpl extends AbstractReferenceTypeService implements ClimateWindfactorTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(ClimateWindfactorType.FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(ClimateConditionType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}
