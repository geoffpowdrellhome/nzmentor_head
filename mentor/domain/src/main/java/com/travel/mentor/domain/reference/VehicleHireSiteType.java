package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( schema = "public", name = "vehicle_hire_site_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="VehicleHireSiteType.findAll", query="SELECT o FROM VehicleHireSiteType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.vehicle_hire_site_type_id_seq", allocationSize = 1)
public class VehicleHireSiteType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_VEHICLE_SITE_TYPES_NAMED_QUERY = "VehicleHireSiteType.findAll";

}

