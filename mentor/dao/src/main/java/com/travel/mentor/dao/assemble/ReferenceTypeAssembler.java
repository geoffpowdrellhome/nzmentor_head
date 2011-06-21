package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.BaseReferenceType;

import java.util.List;

public interface ReferenceTypeAssembler {

    List<ReferenceTypeDTO> assembleToReferenceTypeDTOList(List<BaseReferenceType> baseReferenceTypeList);

    ReferenceTypeDTO assembleToReferenceTypeDTO(BaseReferenceType baseReferenceType);

    BaseReferenceType assembleToReferenceTypeDomainObject(ReferenceTypeDTO referenceTypeDTO);

}
