package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.Audited;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "apartment_site")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "ApartmentSite.findAll", query = "SELECT o FROM ApartmentSite o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.apartment_site_id_seq", allocationSize = 1)
public class ApartmentSite extends Audited {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

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
