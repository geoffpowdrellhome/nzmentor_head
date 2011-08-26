package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecureUserAssembler;
import com.travel.mentor.dao.assemble.security.SecurityRoleAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityRole;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecureUserAssemblerImpl extends AbstractAssembler implements SecureUserAssembler {

    @Resource
    private SecurityRoleAssembler securityRoleAssembler;

    @Override
    public List<SecureUserDTO> assembleToDTOList(List<SecureUser> secureUserList) {
        List<SecureUserDTO> secureUserDTOList = new ArrayList<SecureUserDTO>();
        for (SecureUser secureUser : secureUserList) {
            secureUserDTOList.add( assembleToDTOInstance(secureUser) );
        }
        return secureUserDTOList;
    }

    @Override
    public SecureUser assembleToEntityInstance(SecureUserDTO secureUserDTO) {
        return (SecureUser) assembleUtil.shallowCopy(secureUserDTO, SecureUser.class);
    }

    @Override
    public SecureUserDTO assembleToDTOInstance(SecureUser secureUser) {
        SecureUserDTO secureUserDTO = (SecureUserDTO) assembleUtil.shallowCopy(secureUser, SecureUserDTO.class);
        for (SecurityRole securityRole : secureUser.getSecurityRoleList()) {
            secureUserDTO.getSecurityRoleDTOList().add( securityRoleAssembler.assembleToDTOInstance(securityRole) );
        }
        return secureUserDTO;
    }

    @Override
    public SecureUser deepCopy(SecureUserDTO secureUserDTO, SecureUser secureUser) {
        String[] ignoreProperties = {"id"};
        assembleUtil.shallowCopy(secureUserDTO, secureUser, ignoreProperties);

        for (SecurityRoleDTO securityRoleDTO : secureUserDTO.getSecurityRoleDTOList()) {
            secureUser.getSecurityRoleList().add( securityRoleAssembler.assembleToEntityInstance(securityRoleDTO));
        }

        return secureUser;
    }

}
