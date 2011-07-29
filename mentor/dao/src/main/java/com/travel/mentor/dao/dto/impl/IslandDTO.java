package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;

public class IslandDTO extends AbstractAuditedIdNameDescDTO {

    private CountryDTO countryDTO;

    public IslandDTO() {}

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }
}
