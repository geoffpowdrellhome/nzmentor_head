package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.RoleDTO;

import java.util.List;

public interface RoleDAO {

    List<RoleDTO> findAll();

    //RoleDTO add(RoleDTO roleDTO);

    void delete(RoleDTO roleDTO);

}
