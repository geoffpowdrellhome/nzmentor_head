package com.travel.mentor.domain.base;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractAuditedIdEntity extends AbstractAuditedEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
