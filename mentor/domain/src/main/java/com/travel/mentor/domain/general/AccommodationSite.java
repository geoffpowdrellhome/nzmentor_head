package com.travel.mentor.domain.general;

import com.travel.mentor.domain.reference.AccommodationSiteType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "accommodation_site")
@DiscriminatorValue("AccommodationSite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = AccommodationSite.FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY,
                query = "SELECT o FROM AccommodationSite o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAccommodationSites")})
})
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
