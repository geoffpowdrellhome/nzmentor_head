package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.RoleDTO;
import com.travel.mentor.domain.general.Role;

import java.util.List;

public interface RoleAssembler {

    List<RoleDTO> assembleToDTOList(List<Role> roleList);

    Role assembleToDomainObject(RoleDTO roleDTO);

    RoleDTO assembleToDTO(Role role);

}
