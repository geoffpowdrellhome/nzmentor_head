package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "activity_site_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="ActivitySiteType.findAll", query="SELECT o FROM ActivitySiteType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.activity_site_type_id_seq", allocationSize = 1)
public class ActivitySiteType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY = "ActivitySiteType.findAll";

}
