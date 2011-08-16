package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "footwear_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = FootwearType.FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY,
                query = "SELECT o FROM FootwearType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllFootwearTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.footwear_type_id_seq", allocationSize = 1)
public class FootwearType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY = "FootwearType.findAll";

}
