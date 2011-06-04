package com.travel.mentor.type.impl;

import com.travel.mentor.type.BaseReferenceType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "accommodation_site_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="AccommodationSiteType.findAll", query="SELECT o FROM AccommodationSiteType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.accommodation_site_type_id_seq", allocationSize = 1)
public class AccommodationSiteType extends BaseReferenceType {

    public static final String FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY = "AccommodationSiteType.findAll";

}
