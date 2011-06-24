package com.travel.mentor.dao.dto.security.impl;

import com.travel.mentor.dao.dto.security.MentorGrantedAuthority;

public class MentorGrantedAuthorityImpl implements MentorGrantedAuthority {

    private String rolename;

    public MentorGrantedAuthorityImpl(String _rolename) {
        this.rolename = _rolename;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }

}
