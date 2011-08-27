package com.travel.mentor.domain.security;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "security_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = SecurityGroup.FIND_ALL_SECURITY_GROUPS,
                query = "SELECT o FROM SecurityGroup o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllSecurityGroups")}),
        @NamedQuery(name = SecurityGroup.FIND_SECURITY_GROUPS_BY_LIKE_GROUP_NAME,
                query = "SELECT o FROM SecurityGroup o WHERE o.name like :groupname",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSecurityGroupsByLikeGroupName")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_group_id_seq", allocationSize = 1)
public class SecurityGroup extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SECURITY_GROUPS = "SecurityGroup.findAll";
    public static final String FIND_SECURITY_GROUPS_BY_LIKE_GROUP_NAME = "SecurityGroup.findSecurityGroupsByLikeGroupName";

}
