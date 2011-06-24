package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( schema = "public", name = "location_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="LocationType.findAll", query="SELECT o FROM LocationType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.location_type_id_seq", allocationSize = 1)
public class LocationType extends AbstractAuditedNameDescEntity {

    public static final String FIND_ALL_LOCATION_TYPES_NAMED_QUERY = "LocationType.findAll";

}
