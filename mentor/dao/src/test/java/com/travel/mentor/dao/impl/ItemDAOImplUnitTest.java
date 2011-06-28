package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.ItemDAO;
import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.type.impl.ItemType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ItemDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "itemDAO")
    private ItemDAO itemDAO;

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "accommodationSiteDAO")
    private AccommodationSiteDAO accommodationSiteDAO;

    @Test
    public void testFindAll() {
        List<ItemDTO> itemDTOList = itemDAO.findAll();
        assertRecordsReturned(itemDTOList);
    }

    @Test
    public void testFind() {
        ItemDTO itemDTO = itemDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(itemDTO);
    }

    @Test
    public void testAdd() {
        List<ReferenceTypeDTO> itemTypeDTOList = referenceTypeDAO.findAll(ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY);
        assertRecordsReturned(itemTypeDTOList);
        ReferenceTypeDTO itemTypeDTO = itemTypeDTOList.get(0); // get the first one.

        AccommodationSiteDTO accommodationSiteDTO = accommodationSiteDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(accommodationSiteDTO);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemTypeDTO(itemTypeDTO);
        itemDTO.setHelpfulComments("my helpful comments");
        itemDTO.setDescription("my new item desc");
        itemDTO.setName("my new item");
        itemDTO.setSiteDTO(accommodationSiteDTO);

        itemDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        itemDTO = itemDAO.add(itemDTO);
        Assert.assertNotNull(itemDTO);
    }

    @Test
    public void testDelete() {
        ItemDTO itemDTO = itemDAO.find(EXISTING_ID_VALUE);
        itemDAO.delete(itemDTO);
    }

    @Test
    public void testUpdate() {
        ItemDTO itemDTO = itemDAO.find(EXISTING_ID_VALUE);
        itemDTO.setName("update name 2");
        itemDTO.setDescription("update desc 2");
        itemDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        itemDTO = itemDAO.update(itemDTO);
        Assert.assertNotNull(itemDTO);
    }

}
