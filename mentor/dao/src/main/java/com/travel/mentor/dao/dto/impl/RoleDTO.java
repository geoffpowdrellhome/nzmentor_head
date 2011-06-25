package com.travel.mentor.dao.dto.impl;

import java.io.Serializable;

public class RoleDTO implements Serializable {

    protected Long id;
    protected String rolename;

    public RoleDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
