package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.ItemDTO;
import com.travel.mentor.domain.general.Item;

import java.util.List;

public interface ItemAssembler {

    List<ItemDTO> assembleToDTOList(List<Item> itemList);

    Item assembleToDomainObject(ItemDTO itemDTO);

    ItemDTO assembleToDTO(Item item);

}
