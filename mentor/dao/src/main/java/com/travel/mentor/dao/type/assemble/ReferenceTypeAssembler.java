package com.travel.mentor.dao.type.assemble;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;

import java.util.List;

public interface ReferenceTypeAssembler {

    List<ReferenceTypeDTO> assembleToDTOList(List<AbstractAuditedIdNameDescEntity> abstractAuditedNameDescEntityList);

    AbstractAuditedIdNameDescEntity assembleToDomainObject(ReferenceTypeDTO referenceTypeDTO);

    ReferenceTypeDTO assembleToDTO(AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity);

}
