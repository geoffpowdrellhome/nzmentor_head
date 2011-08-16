package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "item_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY,
                query = "SELECT o FROM ItemType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllItemTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.item_type_id_seq", allocationSize = 1)
public class ItemType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ITEM_TYPES_NAMED_QUERY = "ItemType.findAll";

}
