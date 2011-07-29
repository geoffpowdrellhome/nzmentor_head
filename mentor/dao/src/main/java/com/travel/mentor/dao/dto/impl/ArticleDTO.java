package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;

public class ArticleDTO extends AbstractAuditedIdNameDescDTO {

    private String code;

    public ArticleDTO() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
