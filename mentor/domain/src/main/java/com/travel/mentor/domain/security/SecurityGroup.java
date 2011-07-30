package com.travel.mentor.domain.security;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "security_group")
@NamedQueries({
        @NamedQuery(name = "SecurityGroup.findAll", query = "SELECT o FROM SecurityGroup o order by o.name"),
        @NamedQuery(name = "SecurityGroup.findSecurityGroupsByLikeGroupName",
                query = "SELECT o FROM SecurityGroup o WHERE o.name like :groupname ")
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_group_id_seq", allocationSize = 1)
public class SecurityGroup extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SECURITY_GROUPS = "SecurityGroup.findAll";
    public static final String FIND_SECURITY_GROUPS_BY_LIKE_GROUP_NAME = "SecurityGroup.findSecurityGroupsByLikeGroupName";

}
