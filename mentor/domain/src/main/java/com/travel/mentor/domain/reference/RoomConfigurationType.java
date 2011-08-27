package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "room_configuration_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY,
                query = "SELECT o FROM RoomConfigurationType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllRoomConfigurationTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.room_configuration_type_id_seq", allocationSize = 1)
public class RoomConfigurationType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY = "roomConfigurationType.findAll";

}
