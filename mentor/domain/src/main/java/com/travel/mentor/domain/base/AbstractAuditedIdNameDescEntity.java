package com.travel.mentor.domain.base;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractAuditedIdNameDescEntity extends AbstractAuditedIdEntity {

    @Column(name = "name", nullable = false)
    protected String name="";

    @Column(name = "description", nullable = false)
    protected String description="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
