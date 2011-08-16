package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "accommodation_site_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY,
                query = "SELECT o FROM AccommodationSiteType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllAcommodationSiteTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.accommodation_site_type_id_seq", allocationSize = 1)
public class AccommodationSiteType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY = "AccommodationSiteType.findAll";

}
