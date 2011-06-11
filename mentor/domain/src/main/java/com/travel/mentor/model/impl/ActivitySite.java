package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.Audited;
import com.travel.mentor.type.impl.ActivitySiteType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Table(schema = "public", name = "activity_site")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "ActivitySite.findAll", query = "SELECT o FROM ActivitySite o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.activity_site_id_seq", allocationSize = 1)
public class ActivitySite extends Audited {

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
