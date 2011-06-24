package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.AbstractAuditedNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "island")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "Island.findAll", query = "SELECT o FROM Island o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.island_id_seq", allocationSize = 1)
public class Island extends AbstractAuditedNameDescEntity {

    public static final String FIND_ALL_ISLANDS_NAMED_QUERY = "Island.findAll";

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
