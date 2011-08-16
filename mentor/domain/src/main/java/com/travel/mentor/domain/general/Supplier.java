package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import com.travel.mentor.domain.reference.SupplierType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "supplier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = Supplier.FIND_ALL_SUPPLIERS_NAMED_QUERY,
                query = "SELECT o FROM Supplier o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findSuppliers")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.supplier_id_seq", allocationSize = 1)
public class Supplier extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_SUPPLIERS_NAMED_QUERY = "Supplier.findAll";

    @ManyToOne
    @JoinColumn(name = "supplier_type_id", referencedColumnName = "id")
    private SupplierType supplierType;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    public SupplierType getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(SupplierType supplierType) {
        this.supplierType = supplierType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
