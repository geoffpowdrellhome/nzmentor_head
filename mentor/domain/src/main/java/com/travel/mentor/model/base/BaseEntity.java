package com.travel.mentor.model.base;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity extends Audited {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    protected Long id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "description", nullable = false)
    protected String description;

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
