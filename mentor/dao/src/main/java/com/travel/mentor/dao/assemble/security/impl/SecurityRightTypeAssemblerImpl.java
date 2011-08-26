package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecurityRightTypeAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityRightType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityRightTypeAssemblerImpl extends AbstractAssembler implements SecurityRightTypeAssembler {

    @Override
    public List<SecurityRightTypeDTO> assembleToDTOList(List<SecurityRightType> securityRightTypeList) {
        List<SecurityRightTypeDTO> securityRightTypeDTOList = new ArrayList<SecurityRightTypeDTO>();
        for (SecurityRightType securityRightType : securityRightTypeList) {
            securityRightTypeDTOList.add(assembleToDTOInstance(securityRightType));
        }
        return securityRightTypeDTOList;
    }

    @Override
    public SecurityRightType assembleToEntityInstance(SecurityRightTypeDTO securityRightTypeDTO) {
        SecurityRightType securityRight = (SecurityRightType) assembleUtil.shallowCopy(securityRightTypeDTO, SecurityRightType.class);

        securityRight.setCreateUser((SecureUser) assembleUtil.shallowCopy(securityRightTypeDTO.getCreateUserDTO(), SecureUser.class));
        securityRight.setUpdateUser((SecureUser) assembleUtil.shallowCopy(securityRightTypeDTO.getUpdateUserDTO(), SecureUser.class));

        return securityRight;
    }

    @Override
    public SecurityRightTypeDTO assembleToDTOInstance(SecurityRightType securityRightType) {
        SecurityRightTypeDTO securityRightTypeDTO = (SecurityRightTypeDTO) assembleUtil.shallowCopy(securityRightType, SecurityRightTypeDTO.class);

        securityRightTypeDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityRightType.getCreateUser(), SecureUserDTO.class));
        securityRightTypeDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityRightType.getUpdateUser(), SecureUserDTO.class));

        return securityRightTypeDTO;
    }

    @Override
    public SecurityRightType deepCopy(SecurityRightTypeDTO securityRightTypeDTO, SecurityRightType securityRightType) {
        String[] ignoreProperties = {"id"};
        assembleUtil.shallowCopy(securityRightTypeDTO, securityRightType, ignoreProperties);

        assembleUtil.shallowCopy(securityRightTypeDTO.getCreateUserDTO(), securityRightType.getCreateUser());
        assembleUtil.shallowCopy(securityRightTypeDTO.getUpdateUserDTO(), securityRightType.getUpdateUser());

        return securityRightType;
    }

}
