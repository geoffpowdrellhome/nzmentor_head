package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.general.RoleAssembler;
import com.travel.mentor.dao.assemble.general.UserAssembler;
import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.dto.general.RoleDTO;
import com.travel.mentor.dao.dto.general.UserDTO;
import com.travel.mentor.domain.general.Role;
import com.travel.mentor.domain.general.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserAssemblerImpl extends BaseAssemblerImpl implements UserAssembler {

    @Resource
    private RoleAssembler roleAssembler;

    @Override
    public List<UserDTO> assembleToDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (User user : userList) {
            userDTOList.add( assembleToDTO(user) );
        }
        return userDTOList;
    }

    @Override
    public User assembleToDomainObject(UserDTO userDTO) {
        return (User) shallowCopy(userDTO, User.class);
    }

    @Override
    public UserDTO assembleToDTO(User user) {
        UserDTO userDTO = (UserDTO) shallowCopy(user, UserDTO.class);
//        for (Role role : user.getRoles()) {
//            userDTO.getRoleDTOList().add( roleAssembler.assembleToDTO(role) );
//        }

        return userDTO;
    }

    private RoleDTO assembleToRoleDTO(Role role) {
        return (RoleDTO) shallowCopy(role, RoleDTO.class);
    }

}
