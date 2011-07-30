package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "country")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "Country.findAll", query = "SELECT o FROM Country o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.country_id_seq", allocationSize = 1)
public class Country extends AbstractAuditedIdNameDescEntity {

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
