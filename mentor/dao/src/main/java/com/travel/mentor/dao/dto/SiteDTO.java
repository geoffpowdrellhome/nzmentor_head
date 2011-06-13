package com.travel.mentor.dao.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SiteDTO implements Serializable {

    protected Long id;
    protected String name;
    protected String description;
    protected LocationDTO locationDTO;
    private BigDecimal latitude;
    private BigDecimal longitude;
    protected ReferenceTypeDTO siteTypeDTO;

    public SiteDTO() {}

    public SiteDTO(Long _id,
                   String _name,
                   String _description,
                   LocationDTO _locationDTO,
                   BigDecimal _latitude,
                   BigDecimal _longitude,
                   ReferenceTypeDTO _siteTypeDTO) {

        this.id = _id;
        this.name = _name;
        this.description = _description;
        this.locationDTO = _locationDTO;
        this.latitude = _latitude;
        this.longitude = _longitude;
        this.siteTypeDTO = _siteTypeDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
