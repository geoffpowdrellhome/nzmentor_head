package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "room_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="RoomType.findAll", query="SELECT o FROM RoomType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.room_type_id_seq", allocationSize = 1)
public class RoomType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ROOM_TYPES_NAMED_QUERY = "RoomType.findAll";

}
