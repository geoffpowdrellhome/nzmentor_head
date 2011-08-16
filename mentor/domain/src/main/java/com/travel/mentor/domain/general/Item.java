package com.travel.mentor.domain.general;

import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import com.travel.mentor.domain.reference.ItemType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(value = {
        @NamedQuery(name = Item.FIND_ALL_ITEMS_NAMED_QUERY,
                query = "SELECT o FROM Item o order by o.name",
                hints = {
                        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
                        @QueryHint(name = "org.hibernate.cacheRegion", value = "query.findItems")})
})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.item_id_seq", allocationSize = 1)
public class Item extends AbstractAuditedIdNameDescEntity {

    public static final String FIND_ALL_ITEMS_NAMED_QUERY = "Item.findAll";

    @ManyToOne
    @JoinColumn(name = "item_type_id", referencedColumnName = "id")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name = "helpful_comments")
    private String helpfulComments;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getHelpfulComments() {
        return helpfulComments;
    }

    public void setHelpfulComments(String helpfulComments) {
        this.helpfulComments = helpfulComments;
    }

}
