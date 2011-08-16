package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "public", name = "region")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = Region.FIND_ALL_REGIONS_NAMED_QUERY,
                query = "SELECT o FROM Region o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findRegions")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.region_id_seq", allocationSize = 1)
public class Region extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_REGIONS_NAMED_QUERY = "Region.findAll";

    @ManyToOne
    @JoinColumn(name = "island_id", referencedColumnName = "id")
    private Island island;

    @Column(name = "area_in_square_kms")
    private BigDecimal areaInSquareKilometres;

    @Column(name = "population")
    private Integer population;

    @Column(name = "regional_council_name")
    private String regionalCouncilName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

    public BigDecimal getAreaInSquareKilometres() {
        return areaInSquareKilometres;
    }

    public void setAreaInSquareKilometres(BigDecimal areaInSquareKilometres) {
        this.areaInSquareKilometres = areaInSquareKilometres;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getRegionalCouncilName() {
        return regionalCouncilName;
    }

    public void setRegionalCouncilName(String regionalCouncilName) {
        this.regionalCouncilName = regionalCouncilName;
    }
}
