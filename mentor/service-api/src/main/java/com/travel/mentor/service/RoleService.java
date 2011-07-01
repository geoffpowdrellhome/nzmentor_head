package com.travel.mentor.service;

import com.travel.mentor.dao.dto.impl.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> findAll();

    RoleDTO add(RoleDTO roleDTO);

    void delete(RoleDTO roleDTO);

}
