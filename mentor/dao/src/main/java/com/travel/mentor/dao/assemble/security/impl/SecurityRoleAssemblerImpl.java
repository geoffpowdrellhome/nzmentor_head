package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.security.SecurityRoleAssembler;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;
import com.travel.mentor.model.security.SecurityRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityRoleAssemblerImpl extends BaseAssemblerImpl implements SecurityRoleAssembler {

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
        return (SecurityRole) shallowCopy(securityRoleDTO, SecurityRole.class);
    }

    @Override
    public SecurityRoleDTO assembleToDTO(SecurityRole securityRole) {
        return (SecurityRoleDTO) shallowCopy(securityRole, SecurityRoleDTO.class);
    }

}
