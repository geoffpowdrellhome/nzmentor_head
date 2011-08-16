package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "event_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = EventType.FIND_ALL_EVENT_TYPES_NAMED_QUERY,
                query = "SELECT o FROM EventType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllEventTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.event_type_id_seq", allocationSize = 1)
public class EventType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_EVENT_TYPES_NAMED_QUERY = "EventType.findAll";

}
