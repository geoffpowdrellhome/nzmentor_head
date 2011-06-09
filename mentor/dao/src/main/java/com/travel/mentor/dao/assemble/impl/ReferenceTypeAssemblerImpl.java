package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.ReferenceTypeAssembler;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.type.BaseReferenceType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferenceTypeAssemblerImpl implements ReferenceTypeAssembler {

    @Override
    public List<ReferenceTypeDTO> assembleToReferenceTypeDTOList(List<BaseReferenceType> baseReferenceTypeList) {
        List<ReferenceTypeDTO> referenceTypeDTOList = new ArrayList<ReferenceTypeDTO>();
        for (BaseReferenceType baseReferenceType : baseReferenceTypeList) {
            referenceTypeDTOList.add(assembleToReferenceTypeDTO(baseReferenceType));
        }

        return referenceTypeDTOList;
    }

    public ReferenceTypeDTO assembleToReferenceTypeDTO(BaseReferenceType baseReferenceType) {
        return new ReferenceTypeDTO(baseReferenceType.getId(),
                baseReferenceType.getName(),
                baseReferenceType.getDescription(),
                baseReferenceType.getClass().getName());
    }

    public BaseReferenceType instantiateReferenceTypeDomainObject(ReferenceTypeDTO referenceTypeDTO) {
        try {
            return (BaseReferenceType) Class.forName(referenceTypeDTO.getMappedDomainClassName()).newInstance();
        } catch (Exception ex) {
            return null;
        }
    }

    public BaseReferenceType assembleToReferenceTypeDomainObject(ReferenceTypeDTO referenceTypeDTO) {
        BaseReferenceType baseReferenceType = instantiateReferenceTypeDomainObject(referenceTypeDTO);
        if (baseReferenceType != null) {
            baseReferenceType.setId(referenceTypeDTO.getId());
            baseReferenceType.setDescription(referenceTypeDTO.getDescription());
            baseReferenceType.setName(referenceTypeDTO.getName());
            return baseReferenceType;
        }

        return null;
    }

}
