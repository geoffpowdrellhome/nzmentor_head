package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.general.SpringSecurityUserAssembler;
//import com.travel.mentor.dao.dto.security.MentorGrantedAuthority;
//import com.travel.mentor.dao.dto.security.impl.MentorGrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityUserAssemblerImpl implements SpringSecurityUserAssembler {

    @Override
    public User assembleToSpringSecurityUser(com.travel.mentor.domain.general.User userEntity) {

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
