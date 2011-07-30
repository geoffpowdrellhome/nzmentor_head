package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "currency")
@NamedQuery(name = "Currency.findAll", query = "SELECT o FROM Currency o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.currency_id_seq", allocationSize = 1)
public class Currency extends AbstractAuditedIdNameDescEntity {

    @Column(name = "symbol")
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
