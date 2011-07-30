package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.UserDTO;
import com.travel.mentor.domain.general.User;

import java.util.List;

public interface UserAssembler {

    List<UserDTO> assembleToDTOList(List<User> userList);

    User assembleToDomainObject(UserDTO userDTO);

    UserDTO assembleToDTO(User user);

}
