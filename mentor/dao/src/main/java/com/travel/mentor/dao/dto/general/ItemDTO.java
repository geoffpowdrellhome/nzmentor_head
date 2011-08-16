package com.travel.mentor.dao.dto.general;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;

public class ItemDTO extends AbstractAuditedIdNameDescDTO {
    private ReferenceTypeDTO itemTypeDTO;
    private SiteDTO siteDTO;
    private String helpfulComments;
    private SupplierDTO supplierDTO;

    public ItemDTO() {}

    public SupplierDTO getSupplierDTO() {
        return supplierDTO;
    }

    public void setSupplierDTO(SupplierDTO supplierDTO) {
        this.supplierDTO = supplierDTO;
    }

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
