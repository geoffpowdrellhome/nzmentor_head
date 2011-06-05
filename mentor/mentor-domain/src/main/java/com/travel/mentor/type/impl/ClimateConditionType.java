package com.travel.mentor.type.impl;

import com.travel.mentor.type.BaseReferenceType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "climate_condition_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="ClimateConditionType.findAll", query="SELECT o FROM ClimateConditionType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.climate_condition_type_id_seq", allocationSize = 1)
public class ClimateConditionType extends BaseReferenceType {

    public static final String FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY = "ClimateConditionType.findAll";

}
