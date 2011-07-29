package com.travel.mentor.type.impl;

import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( schema = "public", name = "popularity_ranking_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="PopularityRankingType.findAll", query="SELECT o FROM PopularityRankingType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.popularity_ranking_type_id_seq", allocationSize = 1)
public class PopularityRankingType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY = "PopularityRankingType.findAll";

}



