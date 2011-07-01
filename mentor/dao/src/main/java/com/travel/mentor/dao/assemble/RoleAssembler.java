package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.RoleDTO;
import com.travel.mentor.model.impl.Role;

import java.util.List;

public interface RoleAssembler {

    List<RoleDTO> assembleToDTOList(List<Role> roleList);

    Role assembleToDomainObject(RoleDTO roleDTO);

    RoleDTO assembleToDTO(Role role);

}
