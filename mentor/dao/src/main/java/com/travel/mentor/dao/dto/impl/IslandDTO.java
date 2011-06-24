package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.BaseDTO;

public class IslandDTO extends BaseDTO {

    private CountryDTO countryDTO;

    public IslandDTO() {}

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }
}
