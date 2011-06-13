package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.NameDescAudited;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Table(schema = "public", name = "site")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "Country.findAll", query = "SELECT o FROM Country o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.country_id_seq", allocationSize = 1)
public class Country extends NameDescAudited {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "currency_id", referencedColumnName = "id")
//    private Currency currency;

    @Column(name = "code")
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Currency getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(Currency currency) {
//        this.currency = currency;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
