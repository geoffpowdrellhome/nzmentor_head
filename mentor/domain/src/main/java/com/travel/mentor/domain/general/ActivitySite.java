package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedEntity;
import com.travel.mentor.domain.reference.ActivitySiteType;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;

@Entity
@Table(schema = "public", name = "activity_site")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = ActivitySite.FIND_ALL_ACTIVITY_SITES,
                query = "SELECT o FROM ActivitySite o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findActivitySites")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.activity_site_id_seq", allocationSize = 1)
public class ActivitySite extends AbstractAuditedEntity {

    public static final String FIND_ALL_ACTIVITY_SITES = "ActivitySite.findAll";

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "activity_site_type_id", referencedColumnName = "id")
    private ActivitySiteType activitySiteType;

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

    public ActivitySiteType getActivitySiteType() {
        return activitySiteType;
    }

    public void setActivitySiteType(ActivitySiteType activitySiteType) {
        this.activitySiteType = activitySiteType;
    }
}
