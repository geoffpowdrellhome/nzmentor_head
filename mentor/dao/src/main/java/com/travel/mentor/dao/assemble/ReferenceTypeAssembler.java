package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.model.base.AbstractAuditedNameDescEntity;

import java.util.List;

public interface ReferenceTypeAssembler {

    List<ReferenceTypeDTO> assembleToDTOList(List<AbstractAuditedNameDescEntity> abstractAuditedNameDescEntityList);

    ReferenceTypeDTO assembleToDTO(AbstractAuditedNameDescEntity abstractAuditedNameDescEntity);

    AbstractAuditedNameDescEntity assembleToDomainObject(ReferenceTypeDTO referenceTypeDTO);

}
