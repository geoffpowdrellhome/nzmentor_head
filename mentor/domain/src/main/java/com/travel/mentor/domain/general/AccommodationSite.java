package com.travel.mentor.domain.general;

import com.travel.mentor.domain.reference.AccommodationSiteType;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "accommodation_site")
@DiscriminatorValue("AccommodationSite")
@NamedQuery(name = "AccommodationSite.findAll", query = "SELECT o FROM AccommodationSite o order by o.name")
public class AccommodationSite extends Site {

    public static final String FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY = "AccommodationSite.findAll";

    @ManyToOne
    @JoinColumn(name = "accommodation_site_type_id", referencedColumnName = "id")
    private AccommodationSiteType accommodationSiteType;

    public AccommodationSiteType getAccommodationSiteType() {
        return accommodationSiteType;
    }

    public void setAccommodationSiteType(AccommodationSiteType accommodationSiteType) {
        this.accommodationSiteType = accommodationSiteType;
    }
}
