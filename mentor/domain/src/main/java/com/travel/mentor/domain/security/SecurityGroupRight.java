package com.travel.mentor.domain.security;

import com.travel.mentor.domain.base.AbstractAuditedIdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Table(schema = "public", name = "security_group_right")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS, query = "SELECT o FROM SecurityGroupRight o order by o.securityRight.name", hints = {
                @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                @QueryHint(name = "org.hibernate.cacheRegion", value = SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS),
                @QueryHint(name = "javax.persistence.cache.storeMode", value = "REFRESH")}),
        @NamedQuery(name = SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS_BY_SECURITY_GROUP,
                query = "SELECT o FROM SecurityGroupRight o WHERE o.securityGroup.id =:securitygroupid", hints = {
                @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                @QueryHint(name = "org.hibernate.cacheRegion", value = SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS_BY_SECURITY_GROUP),
                @QueryHint(name = "javax.persistence.cache.storeMode", value = "REFRESH")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_right_id_seq", allocationSize = 1)
public class SecurityGroupRight extends AbstractAuditedIdEntity {

    public static final String FIND_ALL_SECURITY_GROUP_RIGHTS = "SecurityGroupRight.findAll";
    public static final String FIND_ALL_SECURITY_GROUP_RIGHTS_BY_SECURITY_GROUP = "SecurityGroupRight.findSecurityGroupRightsBySecurityGroup";

    @ManyToOne
    @JoinColumn(name = "security_group_id", referencedColumnName = "id")
    private SecurityGroup securityGroup;

    @ManyToOne
    @JoinColumn(name = "security_right_id", referencedColumnName = "id")
    private SecurityRight securityRight;

    public SecurityGroup getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroup = securityGroup;
    }

    public SecurityRight getSecurityRight() {
        return securityRight;
    }

    public void setSecurityRight(SecurityRight securityRight) {
        this.securityRight = securityRight;
    }
}
