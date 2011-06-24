package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( schema = "public", name = "footwear_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="FootwearType.findAll", query="SELECT o FROM FootwearType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.footwear_type_id_seq", allocationSize = 1)
public class FootwearType extends AbstractAuditedNameDescEntity {

    public static final String FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY = "FootwearType.findAll";

}
