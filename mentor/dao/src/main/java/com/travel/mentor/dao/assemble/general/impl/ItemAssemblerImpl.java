package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.general.ItemAssembler;
import com.travel.mentor.dao.dto.general.ItemDTO;
import com.travel.mentor.dao.dto.general.SiteDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.Item;
import com.travel.mentor.domain.general.Site;
import com.travel.mentor.domain.general.Supplier;
import com.travel.mentor.domain.reference.ItemType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemAssemblerImpl extends AbstractAssembler implements ItemAssembler {

    @Override
    public List<ItemDTO> assembleToDTOList(List<Item> itemList) {
        List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
        for (Item item : itemList) {
            itemDTOList.add( assembleToDTOInstance(item) );
        }
        return itemDTOList;
    }

    @Override
    public Item assembleToEntityInstance(ItemDTO itemDTO) {
        Item item = (Item) assembleUtil.shallowCopy(itemDTO, Item.class);

        item.setSupplier((Supplier) assembleUtil.shallowCopy(itemDTO.getSupplierDTO(), Supplier.class));
        item.setItemType((ItemType) assembleUtil.shallowCopy(itemDTO.getItemTypeDTO(), ItemType.class));
        item.setSite((Site) assembleUtil.shallowCopy(itemDTO.getSiteDTO(), Site.class));

        return item;
    }

    @Override
    public ItemDTO assembleToDTOInstance(Item item) {
        ItemDTO itemDTO = (ItemDTO) assembleUtil.shallowCopy(item, ItemDTO.class);

        itemDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(item.getCreateUser(), SecureUserDTO.class));
        itemDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(item.getUpdateUser(), SecureUserDTO.class));

        itemDTO.setItemTypeDTO((ReferenceTypeDTO) assembleUtil.shallowCopy(item.getItemType(), ReferenceTypeDTO.class));
        itemDTO.setSiteDTO((SiteDTO) assembleUtil.shallowCopy(item.getSite(), SiteDTO.class));

        return itemDTO;
    }

    @Override
    public Item deepCopy(ItemDTO itemDTO, Item item) {
        String[] ignoreProperties = {"id"};
        assembleUtil.shallowCopy(itemDTO, item, ignoreProperties);

        assembleUtil.shallowCopy(itemDTO.getCreateUserDTO(), item.getCreateUser());
        assembleUtil.shallowCopy(itemDTO.getUpdateUserDTO(), item.getUpdateUser());
        assembleUtil.shallowCopy(itemDTO.getSupplierDTO(), item.getSupplier());
        assembleUtil.shallowCopy(itemDTO.getItemTypeDTO(), item.getItemType());
        assembleUtil.shallowCopy(itemDTO.getSiteDTO(), item.getSite());

        return item;
    }

}
