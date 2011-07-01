package com.travel.mentor.service.impl;

import com.travel.mentor.dao.RoleDAO;
import com.travel.mentor.dao.dto.impl.RoleDTO;
import com.travel.mentor.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource(name="roleDAO")
    private RoleDAO roleDAO;

    @Override
    public List<RoleDTO> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public RoleDTO add(RoleDTO roleDTO) {
        return roleDAO.add(roleDTO);
    }

    @Override
    public void delete(RoleDTO roleDTO) {
        roleDAO.delete(roleDTO);
    }
}
