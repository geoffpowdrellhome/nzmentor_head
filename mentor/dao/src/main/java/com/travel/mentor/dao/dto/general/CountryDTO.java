package com.travel.mentor.dao.dto.general;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;

public class CountryDTO extends AbstractAuditedIdNameDescDTO {

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
