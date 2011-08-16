package com.travel.mentor.dao.assemble.reference.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.reference.ReferenceTypeAssembler;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferenceTypeAssemblerImpl extends BaseAssemblerImpl implements ReferenceTypeAssembler {

    @Override
    public List<ReferenceTypeDTO> assembleToDTOList(List<AbstractAuditedIdNameDescEntity> abstractAuditedNameDescEntityList) {
        List<ReferenceTypeDTO> referenceTypeDTOList = new ArrayList<ReferenceTypeDTO>();
        for (AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity : abstractAuditedNameDescEntityList) {
            referenceTypeDTOList.add(assembleToDTO(abstractAuditedNameDescEntity));
        }

        return referenceTypeDTOList;
    }

    @Override
    public AbstractAuditedIdNameDescEntity assembleToDomainObject(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity = (AbstractAuditedIdNameDescEntity) shallowCopy(referenceTypeDTO, referenceTypeDTO.getEntityClass());

        abstractAuditedNameDescEntity.setCreateUser((SecureUser) shallowCopy(referenceTypeDTO.getLoggedInUser(), SecureUser.class));
        abstractAuditedNameDescEntity.setUpdateUser((SecureUser) shallowCopy(referenceTypeDTO.getLoggedInUser(), SecureUser.class));

        return abstractAuditedNameDescEntity;
    }


    @Override
    public ReferenceTypeDTO assembleToDTO(AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity) {
        ReferenceTypeDTO referenceTypeDTO = (ReferenceTypeDTO) shallowCopy(abstractAuditedNameDescEntity, ReferenceTypeDTO.class);
        referenceTypeDTO.setEntityClass(abstractAuditedNameDescEntity.getClass());

        referenceTypeDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(abstractAuditedNameDescEntity.getCreateUser(), SecureUserDTO.class));
        referenceTypeDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(abstractAuditedNameDescEntity.getUpdateUser(), SecureUserDTO.class));

        return referenceTypeDTO;
    }

}
