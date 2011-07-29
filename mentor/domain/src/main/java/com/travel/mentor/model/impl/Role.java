package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.AbstractAuditedIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT o FROM Role o order by o.rolename")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.role_id_seq", allocationSize = 1)
public class Role extends AbstractAuditedIdEntity {

    public static final String FIND_ALL_ROLES = "Role.findAll";

    @Column(name = "rolename", nullable = false)
    private String rolename;

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
