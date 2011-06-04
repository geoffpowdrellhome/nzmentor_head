package com.travel.mentor.model.impl;

import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.RoomConfigurationType;
import com.travel.mentor.type.impl.RoomType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "accommodation_site")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "AccommodationSite.findAll", query = "SELECT o FROM AccommodationSite o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.accommodation_site_id_seq", allocationSize = 1)
public class AccommodationSite extends Audited {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "accommodation_site_type_id", referencedColumnName = "id")
    private AccommodationSiteType accommodationSiteType;

    @ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "room_configuration_type_id", referencedColumnName = "id")
    private RoomConfigurationType roomConfigurationType;

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

    public AccommodationSiteType getAccommodationSiteType() {
        return accommodationSiteType;
    }

    public void setAccommodationSiteType(AccommodationSiteType accommodationSiteType) {
        this.accommodationSiteType = accommodationSiteType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomConfigurationType getRoomConfigurationType() {
        return roomConfigurationType;
    }

    public void setRoomConfigurationType(RoomConfigurationType roomConfigurationType) {
        this.roomConfigurationType = roomConfigurationType;
    }
}
