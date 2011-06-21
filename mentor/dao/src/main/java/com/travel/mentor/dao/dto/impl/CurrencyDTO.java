package com.travel.mentor.dao.dto.impl;

import java.io.Serializable;

public class CurrencyDTO implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String symbol;

    public CurrencyDTO() {}

    public CurrencyDTO(Long _id, String _code, String _name, String _symbol) {
        this.id = _id;
        this.code = _code;
        this.name = _name;
        this.symbol = _symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
