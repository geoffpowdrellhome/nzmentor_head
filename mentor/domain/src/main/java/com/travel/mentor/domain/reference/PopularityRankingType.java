package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "popularity_ranking_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = PopularityRankingType.FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY,
                query = "SELECT o FROM PopularityRankingType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllPopularityRankingTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.popularity_ranking_type_id_seq", allocationSize = 1)
public class PopularityRankingType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY = "PopularityRankingType.findAll";

}



