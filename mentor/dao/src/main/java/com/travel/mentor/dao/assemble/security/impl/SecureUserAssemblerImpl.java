package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecureUserAssembler;
import com.travel.mentor.dao.assemble.security.SecurityRoleAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
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
            secureUserDTOList.add( assembleToDTO(secureUser) );
        }
        return secureUserDTOList;
    }

    @Override
    public SecureUser assembleToDomainObject(SecureUserDTO secureUserDTO) {
        return (SecureUser) assembleUtil.shallowCopy(secureUserDTO, SecureUser.class);
    }

    @Override
    public SecureUserDTO assembleToDTO(SecureUser secureUser) {
        SecureUserDTO secureUserDTO = (SecureUserDTO) assembleUtil.shallowCopy(secureUser, SecureUserDTO.class);
        for (SecurityRole securityRole : secureUser.getSecurityRoleList()) {
            secureUserDTO.getSecurityRoleDTOList().add( securityRoleAssembler.assembleToDTO(securityRole) );
        }
        return secureUserDTO;
    }

}
