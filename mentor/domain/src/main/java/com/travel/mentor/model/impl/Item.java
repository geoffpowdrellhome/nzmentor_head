package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.BaseEntity;
import com.travel.mentor.type.impl.ItemType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "item")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "Item.findAll", query = "SELECT o FROM Item o order by o.name")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.item_id_seq", allocationSize = 1)
public class Item extends BaseEntity {

    public static final String FIND_ALL_ITEMS_NAMED_QUERY = "Item.findAll";

    @ManyToOne
    @JoinColumn(name = "item_type_id", referencedColumnName = "id")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site site;

    @Column(name = "helpful_comments")
    private String helpfulComments;

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
