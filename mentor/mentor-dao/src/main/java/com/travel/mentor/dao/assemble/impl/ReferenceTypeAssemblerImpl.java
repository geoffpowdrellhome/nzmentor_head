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
            ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO(baseReferenceType.getId(), baseReferenceType.getName(), baseReferenceType.getDescription());
            referenceTypeDTOList.add(referenceTypeDTO);
        }

        return referenceTypeDTOList;
    }

}
