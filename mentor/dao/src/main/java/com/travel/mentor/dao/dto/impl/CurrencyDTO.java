package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.BaseDTO;

public class CurrencyDTO extends BaseDTO {

    private String code;
    private String symbol;

    public CurrencyDTO() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
