package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "clothing_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = ClothingType.FIND_ALL_CLOTHING_TYPES_NAMED_QUERY,
                query = "SELECT o FROM ClothingType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllClothingTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.clothing_type_id_seq", allocationSize = 1)
public class ClothingType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_CLOTHING_TYPES_NAMED_QUERY = "ClothingType.findAll";

}

