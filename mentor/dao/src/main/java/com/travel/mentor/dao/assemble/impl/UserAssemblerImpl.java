package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.UserAssembler;
import com.travel.mentor.dao.dto.impl.RoleDTO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.model.impl.Role;
import com.travel.mentor.model.impl.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAssemblerImpl extends BaseAssemblerImpl implements UserAssembler {

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
        for (Role role : user.getRoles()) {
            userDTO.getRoleDTOList().add( assembleToRoleDTO(role) );
        }

        return userDTO;
    }

    private RoleDTO assembleToRoleDTO(Role role) {
        return (RoleDTO) shallowCopy(role, RoleDTO.class);
    }

}
