package com.travel.mentor.dao.assemble.reference;

import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;

import java.util.List;

public interface ReferenceTypeAssembler {

    List<ReferenceTypeDTO> assembleToDTOList(List<AbstractAuditedIdNameDescEntity> abstractAuditedNameDescEntityList);

    AbstractAuditedIdNameDescEntity assembleToDomainObject(ReferenceTypeDTO referenceTypeDTO);

    ReferenceTypeDTO assembleToDTO(AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity);

}
