package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.ItemAssembler;
import com.travel.mentor.dao.dto.ItemDTO;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.SiteDTO;
import com.travel.mentor.model.impl.Item;
import com.travel.mentor.model.impl.Site;
import com.travel.mentor.type.impl.ItemType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemAssemblerImpl extends BaseAssemblerImpl implements ItemAssembler {

    @Override
    public List<ItemDTO> assembleToItemDTOList(List<Item> itemList) {
        List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
        for (Item item : itemList) {
            itemDTOList.add( assembleToItemDTO(item) );
        }
        return itemDTOList;
    }

    @Override
    public Item assembleToItemDomainObject(ItemDTO itemDTO) {
        Item item = (Item) shallowCopy(itemDTO, Item.class);
        item.setItemType((ItemType) shallowCopy(itemDTO.getItemTypeDTO(), ItemType.class));
        item.setSite((Site) shallowCopy(itemDTO.getSiteDTO(), Site.class));
        return item;
    }

    @Override
    public ItemDTO assembleToItemDTO(Item item) {
        ItemDTO itemDTO = (ItemDTO) shallowCopy(item, ItemDTO.class);
        itemDTO.setItemTypeDTO((ReferenceTypeDTO) shallowCopy(item.getItemType(), ReferenceTypeDTO.class));
        itemDTO.setSiteDTO((SiteDTO) shallowCopy(item.getSite(), SiteDTO.class));
        return itemDTO;
    }

}
