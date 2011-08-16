package com.travel.mentor.dao.dto.general;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;

public class SupplierDTO extends AbstractAuditedIdNameDescDTO {

    private ReferenceTypeDTO supplierTypeDTO;
    private LocationDTO locationDTO;

    public SupplierDTO() {}

    public ReferenceTypeDTO getSupplierTypeDTO() {
        return supplierTypeDTO;
    }

    public void setSupplierTypeDTO(ReferenceTypeDTO supplierTypeDTO) {
        this.supplierTypeDTO = supplierTypeDTO;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }
}
