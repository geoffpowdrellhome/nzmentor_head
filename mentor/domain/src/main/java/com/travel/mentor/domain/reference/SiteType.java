package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "site_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY,
                query = "SELECT o FROM SiteType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllSiteTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.site_type_id_seq", allocationSize = 1)
public class SiteType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SITE_TYPES_NAMED_QUERY = "SiteType.findAll";

}

