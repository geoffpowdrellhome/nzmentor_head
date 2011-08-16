package com.travel.mentor.domain.security;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "security_right_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = SecurityRightType.FIND_ALL_SECURITY_RIGHT_TYPES_NAMED_QUERY,
                query = "SELECT o FROM SecurityRightType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSecurityRightTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_right_type_id_seq", allocationSize = 1)
public class SecurityRightType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SECURITY_RIGHT_TYPES_NAMED_QUERY = "SecurityRightType.findAll";

}
