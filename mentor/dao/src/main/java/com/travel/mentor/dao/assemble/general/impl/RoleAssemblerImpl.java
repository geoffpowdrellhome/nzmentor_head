package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.general.RoleAssembler;
import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.dto.general.RoleDTO;
import com.travel.mentor.domain.general.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleAssemblerImpl extends BaseAssemblerImpl implements RoleAssembler {

    @Override
    public List<RoleDTO> assembleToDTOList(List<Role> roleList) {
        List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
        for (Role role : roleList) {
            roleDTOList.add( assembleToDTO(role) );
        }
        return roleDTOList;
    }

    @Override
    public Role assembleToDomainObject(RoleDTO roleDTO) {
        return (Role) shallowCopy(roleDTO, Role.class);
    }

    @Override
    public RoleDTO assembleToDTO(Role role) {
        return (RoleDTO) shallowCopy(role, RoleDTO.class);
    }

}
