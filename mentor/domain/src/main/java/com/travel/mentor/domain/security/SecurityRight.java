package com.travel.mentor.domain.security;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "security_right")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = SecurityRight.FIND_ALL_SECURITY_RIGHTS,
                query = "SELECT o FROM SecurityRight o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSecurityRights")}),
        @NamedQuery(name = SecurityRight.FIND_SECURITY_RIGHTS_BY_TYPE,
                query = "SELECT o FROM SecurityRight o WHERE o.securityRightType.id =:securityrighttypeid",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSecurityRightsByType")}),
        @NamedQuery(name = SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME,
                query = "SELECT o FROM SecurityRight o WHERE o.name like :rightname",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSecurityRightsByLikeRightName")}),
        @NamedQuery(name = SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME_AND_TYPE,
                query = "SELECT o FROM SecurityRight o WHERE o.name like :rightname and o.securityRightType.id =:securityrighttypeid",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSecurityRightsByLikeRightNameAndType")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_right_id_seq", allocationSize = 1)
public class SecurityRight extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_SECURITY_RIGHTS_BY_TYPE = "SecurityRight.findSecurityRightByType";
    public static final String FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME = "SecurityRight.findSecurityRightsByLikeRightName";
    public static final String FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME_AND_TYPE = "SecurityRight.findSecurityRightsByLikeRightNameAndType";
    public static final String FIND_ALL_SECURITY_RIGHTS = "SecurityRight.findAll";

    @ManyToOne
    @JoinColumn(name = "security_right_type_id", referencedColumnName = "id")
    private SecurityRightType securityRightType;

    public SecurityRightType getSecurityRightType() {
        return securityRightType;
    }

    public void setSecurityRightType(SecurityRightType securityRightType) {
        this.securityRightType = securityRightType;
    }

}
