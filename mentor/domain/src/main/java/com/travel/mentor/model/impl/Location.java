package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import com.travel.mentor.type.impl.LocationType;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "public", name = "location")
@NamedQuery(name = "Location.findAll", query = "SELECT o FROM Location o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.location_id_seq", allocationSize = 1)
public class Location extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_LOCATIONS_NAMED_QUERY = "Location.findAll";

    @ManyToOne
    @JoinColumn(name = "location_type_id", referencedColumnName = "id")
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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
}
