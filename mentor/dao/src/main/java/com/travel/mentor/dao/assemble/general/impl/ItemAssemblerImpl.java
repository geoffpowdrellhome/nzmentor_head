package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.core.util.AssembleUtil;
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
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemAssemblerImpl extends AbstractAssembler implements ItemAssembler {

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
        Item item = (Item) assembleUtil.shallowCopy(itemDTO, Item.class);

        item.setCreateUser((SecureUser) assembleUtil.shallowCopy(itemDTO.getCreateUserDTO(), SecureUser.class));
        item.setUpdateUser((SecureUser) assembleUtil.shallowCopy(itemDTO.getUpdateUserDTO(), SecureUser.class));

        item.setSupplier((Supplier) assembleUtil.shallowCopy(itemDTO.getSupplierDTO(), Supplier.class));
        item.setItemType((ItemType) assembleUtil.shallowCopy(itemDTO.getItemTypeDTO(), ItemType.class));
        item.setSite((Site) assembleUtil.shallowCopy(itemDTO.getSiteDTO(), Site.class));

        return item;
    }

    @Override
    public ItemDTO assembleToDTO(Item item) {
        ItemDTO itemDTO = (ItemDTO) assembleUtil.shallowCopy(item, ItemDTO.class);

        itemDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(item.getCreateUser(), SecureUserDTO.class));
        itemDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(item.getUpdateUser(), SecureUserDTO.class));

        itemDTO.setItemTypeDTO((ReferenceTypeDTO) assembleUtil.shallowCopy(item.getItemType(), ReferenceTypeDTO.class));
        itemDTO.setSiteDTO((SiteDTO) assembleUtil.shallowCopy(item.getSite(), SiteDTO.class));

        return itemDTO;
    }

}
