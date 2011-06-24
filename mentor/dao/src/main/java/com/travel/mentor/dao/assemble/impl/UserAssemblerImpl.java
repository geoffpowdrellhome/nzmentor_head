package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.UserAssembler;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.model.impl.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAssemblerImpl extends BaseAssemblerImpl implements UserAssembler {

    @Override
    public List<UserDTO> assembleToUserDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (User user : userList) {
            userDTOList.add( assembleToUserDTO(user) );
        }
        return userDTOList;
    }

    @Override
    public User assembleToUserDomainObject(UserDTO userDTO) {
        User user = (User) shallowCopy(userDTO, User.class);
        //region.setIsland( (Island) shallowCopy(regionDTO.getIslandDTO(), Island.class) );
        return user;
    }

    @Override
    public UserDTO assembleToUserDTO(User user) {
        UserDTO userDTO = (UserDTO) shallowCopy(user, UserDTO.class);
        //regionDTO.setIslandDTO( (IslandDTO) shallowCopy(region.getIsland(), IslandDTO.class) );
        return userDTO;
    }


}
