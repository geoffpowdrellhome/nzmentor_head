package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.ItemDAO;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.ItemDTO;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
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

    private void doPostRetrievalAssertsExpectingRecords(List<ItemDTO> itemDTOList) {
        Assert.assertNotNull(itemDTOList);
        Assert.assertTrue(itemDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingReferenceTypes(List<ReferenceTypeDTO> referenceTypeDTOList) {
        Assert.assertNotNull(referenceTypeDTOList);
        Assert.assertTrue(referenceTypeDTOList.size() != 0);
    }

    private void doPostRetrievalAssertsExpectingSites(List<AccommodationSiteDTO> accommodationSiteDTOList) {
        Assert.assertNotNull(accommodationSiteDTOList);
        Assert.assertTrue(accommodationSiteDTOList.size() != 0);
    }

    @Test
    public void testGetAllItems() {
        List<ItemDTO> itemDTOList = itemDAO.findAllItems();
        doPostRetrievalAssertsExpectingRecords(itemDTOList);
    }

    @Test
    public void testAddItem() {

        List<ReferenceTypeDTO> itemTypeDTOList = referenceTypeDAO.findAllReferenceTypes(ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(itemTypeDTOList);
        ReferenceTypeDTO itemTypeDTO = itemTypeDTOList.get(0); // get the first one.

        List<AccommodationSiteDTO> accommodationSiteDTOList = accommodationSiteDAO.findAllAccommodationSites();
        doPostRetrievalAssertsExpectingSites(accommodationSiteDTOList);
        AccommodationSiteDTO siteDTO = accommodationSiteDTOList.get(0); // get the first one.

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemTypeDTO(itemTypeDTO);
        itemDTO.setHelpfulComments("my helpful comments");
        itemDTO.setDescription("my new item desc");
        itemDTO.setName("my new item");
        itemDTO.setSiteDTO(siteDTO);

        itemDAO.addItem(itemDTO);
    }

    @Test
    public void testDeleteItem() {
        List<ItemDTO> itemDTOList = itemDAO.findAllItems();
        doPostRetrievalAssertsExpectingRecords(itemDTOList);
        ItemDTO itemDTO = itemDTOList.get(0); // get the first one.
        itemDAO.deleteItem(itemDTO);
    }

    @Test
    public void testUpdateItem() {
        List<ItemDTO> itemDTOList = itemDAO.findAllItems();
        doPostRetrievalAssertsExpectingRecords(itemDTOList);
        ItemDTO itemDTO = itemDTOList.get(0); // get the first one.
        itemDTO.setName("update name 2");
        itemDTO.setDescription("update desc 2");
        itemDAO.updateItem(itemDTO);
    }

    @Test
    public void testFindItem() {
        List<ItemDTO> itemDTOList = itemDAO.findAllItems();
        doPostRetrievalAssertsExpectingRecords(itemDTOList);
        ItemDTO itemDTO = itemDTOList.get(0); // get the first one.
        itemDTO.setName("update name 2");
        itemDTO.setDescription("update desc 2");
        itemDAO.updateItem(itemDTO);
    }



}
