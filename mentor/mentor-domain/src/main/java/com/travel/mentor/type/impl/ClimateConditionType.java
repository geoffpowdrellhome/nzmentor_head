package com.travel.mentor.type.impl;

import com.travel.mentor.type.BaseReferenceType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "activity_site_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="ClimateConditionType.findAll", query="SELECT o FROM ClimateConditionType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.climate_condition_type_id_seq", allocationSize = 1)
public class ClimateConditionType extends BaseReferenceType {

    public static final String FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY = "ClimateConditionType.findAll";

}
