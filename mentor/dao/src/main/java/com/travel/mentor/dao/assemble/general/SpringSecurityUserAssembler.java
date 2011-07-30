package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.domain.general.User;

public interface SpringSecurityUserAssembler {

    org.springframework.security.core.userdetails.User assembleToSpringSecurityUser(User userEntity);

}
