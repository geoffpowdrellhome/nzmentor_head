package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "climate_windfactor_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="ClimateWindfactorType.findAll", query="SELECT o FROM ClimateWindfactorType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.climate_windfactor_type_id_seq", allocationSize = 1)
public class ClimateWindfactorType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY = "ClimateWindfactorType.findAll";

}
