package com.travel.mentor.dao.dto.base;

import java.io.Serializable;

public class ReferenceTypeDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Class entityClass;

    public ReferenceTypeDTO() {}

    public ReferenceTypeDTO(String _name, String _description, Class _entityClass) {
        this.name = _name;
        this.description = _description;
        this.entityClass = _entityClass;
    }

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

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
