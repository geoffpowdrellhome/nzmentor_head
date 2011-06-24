package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( schema = "public", name = "headwear_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="HeadwearType.findAll", query="SELECT o FROM HeadwearType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.headwear_type_id_seq", allocationSize = 1)
public class HeadwearType extends AbstractAuditedNameDescEntity {

    public static final String FIND_ALL_HEADWEAR_TYPES_NAMED_QUERY = "HeadwearType.findAll";

}

