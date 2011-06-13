package com.travel.mentor.dao.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class LocationDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private ReferenceTypeDTO locationTypeDTO;
    private RegionDTO regionDTO;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public LocationDTO() {}

    public LocationDTO(Long _id,
                       String _name,
                       String _description,
                       ReferenceTypeDTO _locationTypeDTO,
                       RegionDTO _regionDTO,
                       BigDecimal _longitude,
                       BigDecimal _latitude) {
        this.id = _id;
        this.name = _name;
        this.description = _description;
        this.locationTypeDTO = _locationTypeDTO;
        this.regionDTO = _regionDTO;
        this.latitude = _latitude;
        this.longitude = _longitude;
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
