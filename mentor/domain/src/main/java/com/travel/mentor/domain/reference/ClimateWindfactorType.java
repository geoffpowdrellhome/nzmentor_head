package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "climate_windfactor_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = ClimateWindfactorType.FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY,
                query = "SELECT o FROM ClimateWindfactorType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllClimateWindfactorTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.climate_windfactor_type_id_seq", allocationSize = 1)
public class ClimateWindfactorType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY = "ClimateWindfactorType.findAll";

}
