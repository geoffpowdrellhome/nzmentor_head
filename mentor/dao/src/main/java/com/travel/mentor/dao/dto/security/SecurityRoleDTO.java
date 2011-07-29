package com.travel.mentor.dao.dto.security;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdDTO;

public class SecurityRoleDTO extends AbstractAuditedIdDTO {

    private String rolename;
    private String description;

    public SecurityRoleDTO() {}

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
