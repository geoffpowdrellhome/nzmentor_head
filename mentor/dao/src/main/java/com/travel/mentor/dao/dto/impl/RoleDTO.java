package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.AbstractAuditedNameDescDTO;

public class RoleDTO extends AbstractAuditedNameDescDTO {

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
