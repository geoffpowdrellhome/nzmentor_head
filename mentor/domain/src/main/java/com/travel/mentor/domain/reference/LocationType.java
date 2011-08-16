package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "location_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = LocationType.FIND_ALL_LOCATION_TYPES_NAMED_QUERY,
                query = "SELECT o FROM LocationType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllLocationTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.location_type_id_seq", allocationSize = 1)
public class LocationType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_LOCATION_TYPES_NAMED_QUERY = "LocationType.findAll";

}
