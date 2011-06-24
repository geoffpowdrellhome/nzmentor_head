package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.model.impl.User;

import java.util.List;

public interface UserAssembler {

    List<UserDTO> assembleToUserDTOList(List<User> userList);

    User assembleToUserDomainObject(UserDTO userDTO);

    UserDTO assembleToUserDTO(User user);

}
