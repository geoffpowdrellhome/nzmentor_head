package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecurityRoleAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityRole;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityRoleAssemblerImpl extends AbstractAssembler implements SecurityRoleAssembler {

    @Override
    public List<SecurityRoleDTO> assembleToDTOList(List<SecurityRole> securityRoleList) {
        List<SecurityRoleDTO> securityRoleDTOList = new ArrayList<SecurityRoleDTO>();
        for (SecurityRole securityRole : securityRoleList) {
            securityRoleDTOList.add(assembleToDTOInstance(securityRole));
        }
        return securityRoleDTOList;
    }

    @Override
    public SecurityRole assembleToEntityInstance(SecurityRoleDTO securityRoleDTO) {
        SecurityRole securityRight = (SecurityRole) assembleUtil.copyPropertiesToInstantiatedClass(securityRoleDTO, SecurityRole.class);

        securityRight.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityRoleDTO.getCreateUserDTO(), SecureUser.class));
        securityRight.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityRoleDTO.getUpdateUserDTO(), SecureUser.class));

        return securityRight;
    }

    @Override
    public SecurityRoleDTO assembleToDTOInstance(SecurityRole securityRole) {
        SecurityRoleDTO securityRightTypeDTO = (SecurityRoleDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityRole, SecurityRoleDTO.class);

        securityRightTypeDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityRole.getCreateUser(), SecureUserDTO.class));
        securityRightTypeDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityRole.getUpdateUser(), SecureUserDTO.class));

        return securityRightTypeDTO;
    }

    @Override
    public SecurityRole deepCopy(SecurityRoleDTO securityRoleDTO, SecurityRole securityRole) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(securityRoleDTO, securityRole, ignoreProperties);

        BeanUtils.copyProperties(securityRoleDTO.getCreateUserDTO(), securityRole.getCreateUser());
        BeanUtils.copyProperties(securityRoleDTO.getUpdateUserDTO(), securityRole.getUpdateUser());

        return securityRole;
    }

}
