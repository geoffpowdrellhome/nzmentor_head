package com.travel.mentor.service;

import com.travel.mentor.base.AbstractSpringServiceImplTestCase;
import com.travel.mentor.dao.dto.impl.RoleDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class RoleServiceImplUnitTest extends AbstractSpringServiceImplTestCase {

    @Resource(name = "roleService")
    protected RoleService roleService;

    @Test
    public void testFindAll() {
        List<RoleDTO> roleDTOList = roleService.findAll();
        assertRecordsReturned(roleDTOList);
    }

    @Test
    public void testAdd() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRolename("SYS_ADMIN_ROLE");
        roleDTO.setName("Systems Admin Role");
        roleDTO.setDescription("Systems Administrator Role");
        roleDTO.getUserSessionCookieDTO().setUserDTO(userService.find(EXISTING_USERNAME_VALUE));

        roleDTO = roleService.add(roleDTO);
        Assert.assertNotNull(roleDTO);
    }

    @Test
    public void testDelete() {
        List<RoleDTO> roleDTOList = roleService.findAll();
        assertRecordsReturned(roleDTOList);
        roleService.delete(roleDTOList.get(0));
    }

}
