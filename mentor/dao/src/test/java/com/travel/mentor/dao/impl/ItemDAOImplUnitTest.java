package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.ItemDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.type.impl.ItemType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ItemDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "itemDAO")
    private ItemDAO itemDAO;

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "accommodationSiteDAO")
    private AccommodationSiteDAO accommodationSiteDAO;

    @Test
    public void testFindAll() {
        List<ItemDTO> itemDTOList = itemDAO.findAll();
        doExpectingRecordsAssert(itemDTOList);
    }

    @Test
    public void testFind() {
        ItemDTO itemDTO = itemDAO.find(EXISTING_ID_VALUE);
        Assert.assertNotNull(itemDTO);
    }

    @Test
    public void testAdd() {
        List<ReferenceTypeDTO> itemTypeDTOList = referenceTypeDAO.findAll(ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY);
        doExpectingRecordsAssert(itemTypeDTOList);
        ReferenceTypeDTO itemTypeDTO = itemTypeDTOList.get(0); // get the first one.

        List<AccommodationSiteDTO> accommodationSiteDTOList = accommodationSiteDAO.findAll();
        doExpectingRecordsAssert(accommodationSiteDTOList);
        AccommodationSiteDTO siteDTO = accommodationSiteDTOList.get(0); // get the first one.

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemTypeDTO(itemTypeDTO);
        itemDTO.setHelpfulComments("my helpful comments");
        itemDTO.setDescription("my new item desc");
        itemDTO.setName("my new item");
        itemDTO.setSiteDTO(siteDTO);

        itemDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        itemDAO.add(itemDTO);
    }

    @Test
    public void testDelete() {
        ItemDTO itemDTO = itemDAO.find(EXISTING_ID_VALUE);
        itemDAO.delete(itemDTO);
    }

    @Test
    public void testUpdate() {
        ItemDTO itemDTO = itemDAO.find(EXISTING_ID_VALUE);
        itemDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));
        itemDTO.setName("update name 2");
        itemDTO.setDescription("update desc 2");
        itemDAO.update(itemDTO);
    }

}
