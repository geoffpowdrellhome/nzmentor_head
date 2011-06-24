package com.travel.mentor.dao.assemble;

import com.travel.mentor.model.impl.User;

public interface SpringSecurityUserAssembler {

    org.springframework.security.core.userdetails.User assembleToSpringSecurityUser(User userEntity);

}
