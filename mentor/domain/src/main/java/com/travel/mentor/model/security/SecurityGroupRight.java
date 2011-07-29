package com.travel.mentor.model.security;

import com.travel.mentor.model.base.AbstractAuditedIdEntity;
import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Table(schema = "public", name = "security_group_right")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
        @NamedQuery(name = "SecurityGroupRight.findAll", query = "SELECT o FROM SecurityGroupRight o order by o.securityRight.name"),
        @NamedQuery(name = "SecurityGroupRight.findSecurityGroupRightsBySecurityGroup",
                query = "SELECT o FROM SecurityGroupRight o WHERE o.securityGroup.id =:securitygroupid ")
})
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_right_id_seq", allocationSize = 1)
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
