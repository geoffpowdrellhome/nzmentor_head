package com.travel.mentor.service;

import com.travel.mentor.base.AbstractSpringServiceImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.service.type.ItemTypeService;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ItemServiceImplUnitTest extends AbstractSpringServiceImplTestCase {

    @Resource(name = "itemService")
    protected ItemService itemService;

    @Resource(name = "itemTypeService")
    protected ItemTypeService itemTypeService;

    @Resource(name = "accommodationSiteService")
    protected AccommodationSiteService accommodationSiteService;

    @Test
    public void testFindAll() {
        List<ItemDTO> itemDTOList = itemService.findAll();
        assertRecordsReturned(itemDTOList);
    }

    @Test
    public void testAdd() {
        List<ReferenceTypeDTO> itemTypeDTOList = itemTypeService.findAll();
        assertRecordsReturned(itemTypeDTOList);
        ReferenceTypeDTO itemTypeDTO = itemTypeDTOList.get(0); // get the first one.

        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteService.find(EXISTING_ID_VALUE);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setHelpfulComments("helpful comments");
        itemDTO.setItemTypeDTO(itemTypeDTO);
        itemDTO.setSiteDTO(accommodationSiteDTO);
        itemDTO.setDescription("item description");
        itemDTO.setName("item name");
        itemDTO.getUserSessionCookieDTO().setUserDTO(userService.find(EXISTING_USERNAME_VALUE));

        itemDTO = itemService.add(itemDTO);
        Assert.assertNotNull(itemDTO);
    }

    @Test
    public void testUpdate() {
        ItemDTO itemDTO = itemService.find(EXISTING_ID_VALUE);
        itemDTO.setName("update2");
        itemDTO.setDescription("update2");
        itemDTO.getUserSessionCookieDTO().setUserDTO( userService.find(EXISTING_USERNAME_VALUE));

        itemDTO = itemService.update(itemDTO);
        Assert.assertNotNull(itemDTO);
    }

    @Test
    public void testDelete() {
        ItemDTO itemDTO = itemService.find(EXISTING_ID_VALUE);
        itemService.delete(itemDTO);
    }

    @Test
    public void testFind() {
        ItemDTO itemDTO = itemService.find(EXISTING_ID_VALUE);
    }

}

