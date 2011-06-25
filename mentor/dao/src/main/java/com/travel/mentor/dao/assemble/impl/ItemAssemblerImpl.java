package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.ItemAssembler;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.dao.dto.impl.SiteDTO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.model.impl.Item;
import com.travel.mentor.model.impl.Site;
import com.travel.mentor.model.impl.User;
import com.travel.mentor.type.impl.ItemType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemAssemblerImpl extends BaseAssemblerImpl implements ItemAssembler {

    @Override
    public List<ItemDTO> assembleToDTOList(List<Item> itemList) {
        List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
        for (Item item : itemList) {
            itemDTOList.add( assembleToDTO(item) );
        }
        return itemDTOList;
    }

    @Override
    public Item assembleToDomainObject(ItemDTO itemDTO) {
        Item item = (Item) shallowCopy(itemDTO, Item.class);

        item.setCreateUser((User) shallowCopy(itemDTO.getCreateUserDTO(), User.class));
        item.setUpdateUser((User) shallowCopy(itemDTO.getUpdateUserDTO(), User.class));

        item.setItemType((ItemType) shallowCopy(itemDTO.getItemTypeDTO(), ItemType.class));
        item.setSite((Site) shallowCopy(itemDTO.getSiteDTO(), Site.class));
        return item;
    }

    @Override
    public ItemDTO assembleToDTO(Item item) {
        ItemDTO itemDTO = (ItemDTO) shallowCopy(item, ItemDTO.class);

        itemDTO.setCreateUserDTO((UserDTO) shallowCopy(item.getCreateUser(), UserDTO.class));
        itemDTO.setUpdateUserDTO((UserDTO) shallowCopy(item.getUpdateUser(), UserDTO.class));

        itemDTO.setItemTypeDTO((ReferenceTypeDTO) shallowCopy(item.getItemType(), ReferenceTypeDTO.class));
        itemDTO.setSiteDTO((SiteDTO) shallowCopy(item.getSite(), SiteDTO.class));

        return itemDTO;
    }

}
