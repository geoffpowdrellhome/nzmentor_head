package com.travel.mentor.domain.reference;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "transfer_site_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = TransferSiteType.FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY,
                query = "SELECT o FROM TransferSiteType o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findAllTransferSiteTypes")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.transfer_site_type_id_seq", allocationSize = 1)
public class TransferSiteType extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY = "TransferSiteType.findAll";

}
