package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.SpringSecurityUserAssembler;
//import com.travel.mentor.dao.dto.security.MentorGrantedAuthority;
//import com.travel.mentor.dao.dto.security.impl.MentorGrantedAuthorityImpl;
import com.travel.mentor.model.impl.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class SpringSecurityUserAssemblerImpl implements SpringSecurityUserAssembler {

    @Override
    public User assembleToSpringSecurityUser(com.travel.mentor.model.impl.User userEntity) {

        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        boolean enabled = userEntity.isEnabled();
        boolean accountNonExpired = userEntity.isAccountExpired() ? false : true;
        boolean credentialsNonExpired = userEntity.isCredentialsExpired() ? false : true;
        boolean accountNonLocked = userEntity.isAccountLocked() ? false : true;

//        Collection<MentorGrantedAuthority> authorities = new ArrayList<MentorGrantedAuthority>();
//        for (Role role : userEntity.getRoles()) {
//            authorities.add(new MentorGrantedAuthorityImpl(role.getRolename()));
//        }
//
//        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(username, password, enabled,
//                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        return null; //userDetails;
    }

}
