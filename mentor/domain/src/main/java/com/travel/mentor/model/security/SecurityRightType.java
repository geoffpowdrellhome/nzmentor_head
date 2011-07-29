package com.travel.mentor.model.security;

import com.travel.mentor.model.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "security_right_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="SecurityRightType.findAll", query="SELECT o FROM SecurityRightType o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.security_right_type_id_seq", allocationSize = 1)
public class SecurityRightType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SECURITY_RIGHT_TYPES_NAMED_QUERY = "SecurityRightType.findAll";

}
