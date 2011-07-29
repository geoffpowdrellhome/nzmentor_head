package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "clothing_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="ClothingType.findAll", query="SELECT o FROM ClothingType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.clothing_type_id_seq", allocationSize = 1)
public class ClothingType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_CLOTHING_TYPES_NAMED_QUERY = "ClothingType.findAll";

}

