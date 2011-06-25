package com.travel.mentor.dao.dto.base;

public class ReferenceTypeDTO extends AbstractAuditedNameDescDTO {

    protected Class entityClass;

    public ReferenceTypeDTO() {}

    public ReferenceTypeDTO(String _name, String _description, Class _entityClass) {
        this.name = _name;
        this.description = _description;
        this.entityClass = _entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
