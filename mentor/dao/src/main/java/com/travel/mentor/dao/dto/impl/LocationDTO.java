package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import java.math.BigDecimal;

public class LocationDTO extends AbstractAuditedIdNameDescDTO {

    private ReferenceTypeDTO locationTypeDTO;
    private RegionDTO regionDTO;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public LocationDTO() {}

    public ReferenceTypeDTO getLocationTypeDTO() {
        return locationTypeDTO;
    }

    public void setLocationTypeDTO(ReferenceTypeDTO locationTypeDTO) {
        this.locationTypeDTO = locationTypeDTO;
    }

    public RegionDTO getRegionDTO() {
        return regionDTO;
    }

    public void setRegionDTO(RegionDTO regionDTO) {
        this.regionDTO = regionDTO;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
