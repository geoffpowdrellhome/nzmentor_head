package com.travel.mentor.dao.dto;

import java.io.Serializable;

public class ReferenceTypeDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String mappedDomainClassName;

    public ReferenceTypeDTO() {}

    public ReferenceTypeDTO(Long _id, String _name, String _description, String _mappedDomainClassName) {
        this.id = _id;
        this.name = _name;
        this.description = _description;
        this.mappedDomainClassName = _mappedDomainClassName;
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

    public String getMappedDomainClassName() {
        return mappedDomainClassName;
    }

    public void setMappedDomainClassName(String mappedDomainClassName) {
        this.mappedDomainClassName = mappedDomainClassName;
    }
}
