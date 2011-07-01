package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.RoleDAO;
import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.impl.RoleDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class RoleDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "roleDAO")
    private RoleDAO roleDAO;

    @Test
    public void testFindAll() {
        List<RoleDTO> roleDTOList = roleDAO.findAll();
        assertRecordsReturned(roleDTOList);
    }

    @Test
    public void testAdd() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRolename("SYS_ADMIN_ROLE");
        roleDTO.setName("Systems Admin Role");
        roleDTO.setDescription("Systems Administrator Role");
        roleDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        roleDTO = roleDAO.add(roleDTO);
        Assert.assertNotNull(roleDTO);
    }

    @Test
    public void testDelete() {
        List<RoleDTO> roleDTOList = roleDAO.findAll();
        assertRecordsReturned(roleDTOList);
        roleDAO.delete(roleDTOList.get(0));
    }

}
