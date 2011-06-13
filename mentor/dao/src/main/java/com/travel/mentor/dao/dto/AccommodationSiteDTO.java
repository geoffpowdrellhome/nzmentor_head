package com.travel.mentor.dao.dto;

public class AccommodationSiteDTO extends SiteDTO {

    private ReferenceTypeDTO accommodationSiteTypeDTO;
    private ReferenceTypeDTO roomTypeDTO;
    private ReferenceTypeDTO roomConfigurationTypeDTO;

    public AccommodationSiteDTO() {}

    public AccommodationSiteDTO(Long _id,
                                String _name,
                                String _description,
                                ReferenceTypeDTO _siteTypeDTO,
                                ReferenceTypeDTO _accommodationSiteTypeDTO,
                                ReferenceTypeDTO _roomTypeDTO,
                                ReferenceTypeDTO _roomConfigurationTypeDTO,
                                LocationDTO _locationDTO) {
        this.id = _id;
        this.name = _name;
        this.description = _description;
        this.siteTypeDTO = _siteTypeDTO;
        this.accommodationSiteTypeDTO = _accommodationSiteTypeDTO;
        this.roomTypeDTO = _roomTypeDTO;
        this.roomConfigurationTypeDTO = _roomConfigurationTypeDTO;
        this.locationDTO = _locationDTO;
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

    public ReferenceTypeDTO getAccommodationSiteTypeDTO() {
        return accommodationSiteTypeDTO;
    }

    public void setAccommodationSiteTypeDTO(ReferenceTypeDTO accommodationSiteTypeDTO) {
        this.accommodationSiteTypeDTO = accommodationSiteTypeDTO;
    }

    public ReferenceTypeDTO getRoomTypeDTO() {
        return roomTypeDTO;
    }

    public void setRoomTypeDTO(ReferenceTypeDTO roomTypeDTO) {
        this.roomTypeDTO = roomTypeDTO;
    }

    public ReferenceTypeDTO getRoomConfigurationTypeDTO() {
        return roomConfigurationTypeDTO;
    }

    public void setRoomConfigurationTypeDTO(ReferenceTypeDTO roomConfigurationTypeDTO) {
        this.roomConfigurationTypeDTO = roomConfigurationTypeDTO;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }
}
