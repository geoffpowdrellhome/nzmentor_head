package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "island")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = Island.FIND_ALL_ISLANDS_NAMED_QUERY,
                query = "SELECT o FROM Island o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllIslands")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.island_id_seq", allocationSize = 1)
public class Island extends AbstractAuditedIdNameDescEntity {

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
