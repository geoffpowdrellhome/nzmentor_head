package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.AbstractAuditedNameDescDTO;

public class IslandDTO extends AbstractAuditedNameDescDTO {

    private CountryDTO countryDTO;

    public IslandDTO() {}

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }
}
