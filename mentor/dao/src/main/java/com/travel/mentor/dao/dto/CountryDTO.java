package com.travel.mentor.dao.dto;

import java.io.Serializable;

public class CountryDTO implements Serializable {

    private Long id;
    private CurrencyDTO currencyDTO;
    private String code;
    private String name;

    public CountryDTO() {}

    public CountryDTO(Long _id, CurrencyDTO _currencyDTO, String _code, String _name) {
        this.id = _id;
        this.currencyDTO = _currencyDTO;
        this.code = _code;
        this.name = _name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
