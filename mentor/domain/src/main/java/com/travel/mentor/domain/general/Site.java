package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import com.travel.mentor.domain.reference.SiteType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "public", name = "site")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, name="siteTypeId")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = Site.FIND_ALL_SITES_NAMED_QUERY,
                query = "SELECT o FROM Site o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllSites")})
})
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.site_id_seq", allocationSize = 1)
public class Site extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SITES_NAMED_QUERY = "Site.findAll";

    @ManyToOne
    @JoinColumn(name = "site_type_id", referencedColumnName = "id")
    protected SiteType siteType;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    protected Location location;

    @Column(name = "latitude")
    protected BigDecimal latitude;

    @Column(name = "longitude")
    protected BigDecimal longitude;

    public SiteType getSiteType() {
        return siteType;
    }

    public void setSiteType(SiteType siteType) {
        this.siteType = siteType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
