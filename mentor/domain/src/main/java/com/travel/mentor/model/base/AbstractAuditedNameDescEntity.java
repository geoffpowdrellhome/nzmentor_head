package com.travel.mentor.model.base;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractAuditedNameDescEntity extends AbstractAuditedEntity {

    @Column(name = "name", nullable = false)
    public String name="";

    @Column(name = "description", nullable = false)
    protected String description="";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
