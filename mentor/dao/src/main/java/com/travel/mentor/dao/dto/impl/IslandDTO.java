package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.impl.CountryDTO;

import java.io.Serializable;

public class IslandDTO implements Serializable {

    private Long id;
    private String name;
    private CountryDTO countryDTO;

    public IslandDTO() {}

    public IslandDTO(Long _id, String _name, CountryDTO _countryDTO) {
        this.id = _id;
        this.name = _name;
        this.countryDTO = _countryDTO;
    }

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

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }
}
