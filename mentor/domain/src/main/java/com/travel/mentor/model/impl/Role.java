package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.AuditedEntity;
import javax.persistence.*;

@Entity
@Table(schema = "public", name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT o FROM Role o order by o.rolename")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.role_id_seq", allocationSize = 1)
public class Role extends AuditedEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

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
