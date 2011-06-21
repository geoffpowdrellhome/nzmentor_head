package com.travel.mentor.dao.dto.impl;

import com.travel.mentor.dao.dto.base.BaseDTO;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;

import java.math.BigDecimal;

public class SiteDTO extends BaseDTO {

    protected LocationDTO locationDTO;
    private BigDecimal latitude;
    private BigDecimal longitude;
    protected ReferenceTypeDTO siteTypeDTO;

    public SiteDTO() {}

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public ReferenceTypeDTO getSiteTypeDTO() {
        return siteTypeDTO;
    }

    public void setSiteTypeDTO(ReferenceTypeDTO siteTypeDTO) {
        this.siteTypeDTO = siteTypeDTO;
    }
}
