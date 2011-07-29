package com.travel.mentor.dao.dto.base;

public abstract class AbstractAuditedIdDTO extends AbstractAuditedDTO {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
