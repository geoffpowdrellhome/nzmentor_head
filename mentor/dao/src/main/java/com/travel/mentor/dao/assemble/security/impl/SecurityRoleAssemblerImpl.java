package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecurityRoleAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityRoleAssemblerImpl extends AbstractAssembler implements SecurityRoleAssembler {

    @Override
    public List<SecurityRoleDTO> assembleToDTOList(List<SecurityRole> securityRoleList) {
        List<SecurityRoleDTO> securityRoleDTOList = new ArrayList<SecurityRoleDTO>();
        for (SecurityRole securityRole : securityRoleList) {
            securityRoleDTOList.add( assembleToDTO(securityRole) );
        }
        return securityRoleDTOList;
    }

    @Override
    public SecurityRole assembleToDomainObject(SecurityRoleDTO securityRoleDTO) {
        SecurityRole securityRight = (SecurityRole) assembleUtil.shallowCopy(securityRoleDTO, SecurityRole.class);

        securityRight.setCreateUser((SecureUser) assembleUtil.shallowCopy(securityRoleDTO.getCreateUserDTO(), SecureUser.class));
        securityRight.setUpdateUser((SecureUser) assembleUtil.shallowCopy(securityRoleDTO.getUpdateUserDTO(), SecureUser.class));

        return securityRight;
    }

    @Override
    public SecurityRoleDTO assembleToDTO(SecurityRole securityRole) {
        SecurityRoleDTO securityRightTypeDTO = (SecurityRoleDTO) assembleUtil.shallowCopy(securityRole, SecurityRoleDTO.class);

        securityRightTypeDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityRole.getCreateUser(), SecureUserDTO.class));
        securityRightTypeDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityRole.getUpdateUser(), SecureUserDTO.class));

        return securityRightTypeDTO;
    }

}
