package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.ReferenceTypeAssembler;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.BaseReferenceType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferenceTypeAssemblerImpl extends BaseAssemblerImpl implements ReferenceTypeAssembler {

    @Override
    public List<ReferenceTypeDTO> assembleToReferenceTypeDTOList(List<BaseReferenceType> baseReferenceTypeList) {
        List<ReferenceTypeDTO> referenceTypeDTOList = new ArrayList<ReferenceTypeDTO>();
        for (BaseReferenceType baseReferenceType : baseReferenceTypeList) {
            referenceTypeDTOList.add(assembleToReferenceTypeDTO(baseReferenceType));
        }

        return referenceTypeDTOList;
    }

    public ReferenceTypeDTO assembleToReferenceTypeDTO(BaseReferenceType baseReferenceType) {
        ReferenceTypeDTO referenceTypeDTO = (ReferenceTypeDTO) shallowCopy(baseReferenceType, ReferenceTypeDTO.class);
        referenceTypeDTO.setEntityClass(baseReferenceType.getClass());
        return referenceTypeDTO;
    }

    @Override
    public BaseReferenceType assembleToReferenceTypeDomainObject(ReferenceTypeDTO referenceTypeDTO) {
        BaseReferenceType baseReferenceType = (BaseReferenceType) shallowCopy(referenceTypeDTO, referenceTypeDTO.getEntityClass());
        return baseReferenceType;
    }

}
