package com.travel.mentor.domain.security;

import com.travel.mentor.domain.base.AbstractAuditedIdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "security_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = SecurityRole.FIND_ALL_SECURITY_ROLES, query = "SELECT o FROM SecurityRole o order by o.rolename", hints = {
                @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllSecurityRoles")}),
        @NamedQuery(name = SecurityRole.FIND_SECURITY_ROLES_BY_LIKE_ROLE_NAME,
                query = "SELECT o FROM SecurityRole o WHERE o.rolename like :rolename ", hints = {
                @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSecurityRolesByLikeRoleName")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_role_id_seq", allocationSize = 1)
public class SecurityRole extends AbstractAuditedIdEntity {

    public static final String FIND_ALL_SECURITY_ROLES = "SecurityRole.findAll";
    public static final String FIND_SECURITY_ROLES_BY_LIKE_ROLE_NAME = "SecurityRole.findSecurityRolesByLikeRoleName";

    @Column(name = "rolename", nullable = false)
    private String rolename;

    @Column(name = "description", nullable = false)
    private String description;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
