package com.travel.mentor.dao.dto;

import java.math.BigDecimal;

public class RegionDTO extends BaseDTO {

    private IslandDTO islandDTO;
    private BigDecimal areaInSquareKilometres;
    private Integer population;
    private String regionalCouncilName;

    public RegionDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IslandDTO getIslandDTO() {
        return islandDTO;
    }

    public void setIslandDTO(IslandDTO islandDTO) {
        this.islandDTO = islandDTO;
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
