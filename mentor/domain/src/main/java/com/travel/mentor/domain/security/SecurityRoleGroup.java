package com.travel.mentor.domain.security;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "security_role_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="SecurityRoleGroup.findAll", query="SELECT o FROM SecurityRoleGroup o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_role_group_id_seq", allocationSize = 1)
public class SecurityRoleGroup extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SECURITY_ROLE_GROUPS = "SecurityRoleGroup.findAll";

    @ManyToOne
    @JoinColumn(name = "security_group_id", referencedColumnName = "id")
    private SecurityGroup securityGroup;

    @ManyToOne
    @JoinColumn(name = "security_role_id", referencedColumnName = "id")
    private SecurityRole securityRole;

    public SecurityGroup getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroup = securityGroup;
    }

    public SecurityRole getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(SecurityRole securityRole) {
        this.securityRole = securityRole;
    }
}
