package com.travel.mentor.dao.general;

import com.travel.mentor.dao.dto.general.RoleDTO;

import java.util.List;

public interface RoleDAO {

    List<RoleDTO> findAll();

    //RoleDTO add(RoleDTO roleDTO);

    void delete(RoleDTO roleDTO);

}
