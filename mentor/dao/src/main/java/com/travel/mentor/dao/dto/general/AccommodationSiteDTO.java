package com.travel.mentor.dao.dto.general;

import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;

public class AccommodationSiteDTO extends SiteDTO {

    private ReferenceTypeDTO accommodationSiteTypeDTO;

    public AccommodationSiteDTO() {}

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

    public ReferenceTypeDTO getAccommodationSiteTypeDTO() {
        return accommodationSiteTypeDTO;
    }

    public void setAccommodationSiteTypeDTO(ReferenceTypeDTO accommodationSiteTypeDTO) {
        this.accommodationSiteTypeDTO = accommodationSiteTypeDTO;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }
}
