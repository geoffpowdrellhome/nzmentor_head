package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.ClothingTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.ClimateConditionType;
import com.travel.mentor.type.impl.ClothingType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("clothingTypeService")
@Transactional
public class ClothingTypeServiceImpl extends AbstractReferenceTypeService implements ClothingTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(ClothingType.FIND_ALL_CLOTHING_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(ClimateConditionType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}
