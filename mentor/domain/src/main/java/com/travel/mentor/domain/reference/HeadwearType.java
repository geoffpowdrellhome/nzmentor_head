package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "headwear_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = HeadwearType.FIND_ALL_HEADWEAR_TYPES_NAMED_QUERY,
                query = "SELECT o FROM HeadwearType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllHeadwearTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.headwear_type_id_seq", allocationSize = 1)
public class HeadwearType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_HEADWEAR_TYPES_NAMED_QUERY = "HeadwearType.findAll";

}

