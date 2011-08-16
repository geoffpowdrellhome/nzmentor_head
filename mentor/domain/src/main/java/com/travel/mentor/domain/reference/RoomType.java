package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "room_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY,
                query = "SELECT o FROM RoomType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllRoomTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.location_type_id_seq", allocationSize = 1)
public class RoomType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ROOM_TYPES_NAMED_QUERY = "RoomType.findAll";

}
