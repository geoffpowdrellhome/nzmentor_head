package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( schema = "public", name = "item_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="ItemType.findAll", query="SELECT o FROM ItemType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.item_type_id_seq", allocationSize = 1)
public class ItemType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ITEM_TYPES_NAMED_QUERY = "ItemType.findAll";

}
