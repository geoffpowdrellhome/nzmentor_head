package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.ReferenceTypeAssembler;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.model.base.AbstractAuditedNameDescEntity;
import com.travel.mentor.model.impl.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferenceTypeAssemblerImpl extends BaseAssemblerImpl implements ReferenceTypeAssembler {

    @Override
    public List<ReferenceTypeDTO> assembleToDTOList(List<AbstractAuditedNameDescEntity> abstractAuditedNameDescEntityList) {
        List<ReferenceTypeDTO> referenceTypeDTOList = new ArrayList<ReferenceTypeDTO>();
        for (AbstractAuditedNameDescEntity abstractAuditedNameDescEntity : abstractAuditedNameDescEntityList) {
            referenceTypeDTOList.add(assembleToDTO(abstractAuditedNameDescEntity));
        }

        return referenceTypeDTOList;
    }

    @Override
    public AbstractAuditedNameDescEntity assembleToDomainObject(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedNameDescEntity abstractAuditedNameDescEntity = (AbstractAuditedNameDescEntity) shallowCopy(referenceTypeDTO, referenceTypeDTO.getEntityClass());

        abstractAuditedNameDescEntity.setCreateUser((User) shallowCopy(referenceTypeDTO.getCreateUserDTO(), User.class));
        abstractAuditedNameDescEntity.setUpdateUser((User) shallowCopy(referenceTypeDTO.getUpdateUserDTO(), User.class));

        return abstractAuditedNameDescEntity;
    }


    @Override
    public ReferenceTypeDTO assembleToDTO(AbstractAuditedNameDescEntity abstractAuditedNameDescEntity) {
        ReferenceTypeDTO referenceTypeDTO = (ReferenceTypeDTO) shallowCopy(abstractAuditedNameDescEntity, ReferenceTypeDTO.class);
        referenceTypeDTO.setEntityClass(abstractAuditedNameDescEntity.getClass());

        referenceTypeDTO.setCreateUserDTO((UserDTO) shallowCopy(abstractAuditedNameDescEntity.getCreateUser(), UserDTO.class));
        referenceTypeDTO.setUpdateUserDTO((UserDTO) shallowCopy(abstractAuditedNameDescEntity.getUpdateUser(), UserDTO.class));

        return referenceTypeDTO;
    }

}
