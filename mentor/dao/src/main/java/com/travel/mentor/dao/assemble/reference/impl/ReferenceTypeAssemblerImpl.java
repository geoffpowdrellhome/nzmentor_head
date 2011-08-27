package com.travel.mentor.dao.assemble.reference.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.reference.ReferenceTypeAssembler;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferenceTypeAssemblerImpl extends AbstractAssembler implements ReferenceTypeAssembler {

    @Override
    public List<ReferenceTypeDTO> assembleToDTOList(List<AbstractAuditedIdNameDescEntity> abstractAuditedNameDescEntityList) {
        List<ReferenceTypeDTO> referenceTypeDTOList = new ArrayList<ReferenceTypeDTO>();
        for (AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity : abstractAuditedNameDescEntityList) {
            referenceTypeDTOList.add(assembleToDTOInstance(abstractAuditedNameDescEntity));
        }
        return referenceTypeDTOList;
    }

    @Override
    public AbstractAuditedIdNameDescEntity assembleToEntityInstance(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity = (AbstractAuditedIdNameDescEntity) assembleUtil.copyPropertiesToInstantiatedClass(referenceTypeDTO, referenceTypeDTO.getEntityClass());

        abstractAuditedNameDescEntity.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(referenceTypeDTO.getLoggedInUser(), SecureUser.class));
        abstractAuditedNameDescEntity.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(referenceTypeDTO.getLoggedInUser(), SecureUser.class));

        return abstractAuditedNameDescEntity;
    }


    @Override
    public ReferenceTypeDTO assembleToDTOInstance(AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity) {
        ReferenceTypeDTO referenceTypeDTO = (ReferenceTypeDTO) assembleUtil.copyPropertiesToInstantiatedClass(abstractAuditedNameDescEntity, ReferenceTypeDTO.class);
        referenceTypeDTO.setEntityClass(abstractAuditedNameDescEntity.getClass());

        referenceTypeDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(abstractAuditedNameDescEntity.getCreateUser(), SecureUserDTO.class));
        referenceTypeDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(abstractAuditedNameDescEntity.getUpdateUser(), SecureUserDTO.class));

        return referenceTypeDTO;
    }

    @Override
    public AbstractAuditedIdNameDescEntity deepCopy(ReferenceTypeDTO referenceTypeDTO, AbstractAuditedIdNameDescEntity abstractAuditedIdNameDescEntity) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(referenceTypeDTO, abstractAuditedIdNameDescEntity, ignoreProperties);

        BeanUtils.copyProperties(referenceTypeDTO.getCreateUserDTO(), abstractAuditedIdNameDescEntity.getCreateUser());
        BeanUtils.copyProperties(referenceTypeDTO.getUpdateUserDTO(), abstractAuditedIdNameDescEntity.getUpdateUser());

        return abstractAuditedIdNameDescEntity;
    }

}
