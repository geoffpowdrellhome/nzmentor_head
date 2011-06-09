package com.travel.mentor.type.impl;

import com.travel.mentor.type.BaseReferenceType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "event_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="EventType.findAll", query="SELECT o FROM EventType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.event_type_id_seq", allocationSize = 1)
public class EventType extends BaseReferenceType {

    public static final String FIND_ALL_EVENT_TYPES_NAMED_QUERY = "EventType.findAll";

}
