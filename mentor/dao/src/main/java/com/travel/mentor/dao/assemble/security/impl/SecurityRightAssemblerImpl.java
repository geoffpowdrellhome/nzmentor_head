package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecurityRightAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityRight;
import com.travel.mentor.domain.security.SecurityRightType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityRightAssemblerImpl extends AbstractAssembler implements SecurityRightAssembler {

    @Override
    public List<SecurityRightDTO> assembleToDTOList(List<SecurityRight> securityRightList) {
        List<SecurityRightDTO> securityRightDTOList = new ArrayList<SecurityRightDTO>();
        for (SecurityRight securityRight : securityRightList) {
            securityRightDTOList.add(assembleToDTOInstance(securityRight));
        }
        return securityRightDTOList;
    }

    @Override
    public SecurityRight assembleToEntityInstance(SecurityRightDTO securityRightDTO) {
        SecurityRight securityRight = (SecurityRight) assembleUtil.copyPropertiesToInstantiatedClass(securityRightDTO, SecurityRight.class);

        securityRight.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityRightDTO.getCreateUserDTO(), SecureUser.class));
        securityRight.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityRightDTO.getUpdateUserDTO(), SecureUser.class));
        securityRight.setSecurityRightType((SecurityRightType) assembleUtil.copyPropertiesToInstantiatedClass(securityRightDTO.getSecurityRightTypeDTO(), SecurityRightType.class));

        return securityRight;
    }

    @Override
    public SecurityRightDTO assembleToDTOInstance(SecurityRight securityRight) {
        SecurityRightDTO securityRightDTO = (SecurityRightDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityRight, SecurityRightDTO.class);

        securityRightDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityRight.getCreateUser(), SecureUserDTO.class));
        securityRightDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityRight.getUpdateUser(), SecureUserDTO.class));
        securityRightDTO.setSecurityRightTypeDTO((SecurityRightTypeDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityRight.getSecurityRightType(), SecurityRightTypeDTO.class));

        return securityRightDTO;
    }

    @Override
    public SecurityRight deepCopy(SecurityRightDTO securityRightDTO, SecurityRight securityRight) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(securityRightDTO, securityRight, ignoreProperties);

        BeanUtils.copyProperties(securityRightDTO.getCreateUserDTO(), securityRight.getCreateUser());
        BeanUtils.copyProperties(securityRightDTO.getUpdateUserDTO(), securityRight.getUpdateUser());
        BeanUtils.copyProperties(securityRightDTO.getSecurityRightTypeDTO(), securityRight.getSecurityRightType());

        return securityRight;
    }

}
