package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "room_configuration_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="RoomConfigurationType.findAll", query="SELECT o FROM RoomConfigurationType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.room_configuration_type_id_seq", allocationSize = 1)
public class RoomConfigurationType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY = "RoomConfigurationType.findAll";

}
