package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;

public class ItemDTO extends AbstractAuditedIdNameDescDTO {
    private ReferenceTypeDTO itemTypeDTO;
    private SiteDTO siteDTO;
    private String helpfulComments;

    public ItemDTO() {}

    public ReferenceTypeDTO getItemTypeDTO() {
        return itemTypeDTO;
    }

    public void setItemTypeDTO(ReferenceTypeDTO itemTypeDTO) {
        this.itemTypeDTO = itemTypeDTO;
    }

    public SiteDTO getSiteDTO() {
        return siteDTO;
    }

    public void setSiteDTO(SiteDTO siteDTO) {
        this.siteDTO = siteDTO;
    }

    public String getHelpfulComments() {
        return helpfulComments;
    }

    public void setHelpfulComments(String helpfulComments) {
        this.helpfulComments = helpfulComments;
    }
}
