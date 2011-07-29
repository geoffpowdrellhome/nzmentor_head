package com.travel.mentor.dao.dto.base;

public class ReferenceTypeDTO extends AbstractAuditedIdNameDescDTO {

    protected Class entityClass;

    public ReferenceTypeDTO() {}

    public ReferenceTypeDTO(String _name, String _description) {
        this.name = _name;
        this.description = _description;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
