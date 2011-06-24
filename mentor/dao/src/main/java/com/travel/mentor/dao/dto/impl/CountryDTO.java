package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.BaseDTO;

public class CountryDTO extends BaseDTO {

    private CurrencyDTO currencyDTO;
    private String code;

    public CountryDTO() {}

    public CurrencyDTO getCurrencyDTO() {
        return currencyDTO;
    }

    public void setCurrencyDTO(CurrencyDTO currencyDTO) {
        this.currencyDTO = currencyDTO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
