package com.travel.mentor.dao.general;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.general.ItemDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.domain.reference.ItemType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ItemDAOImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "itemDAO")
    private ItemDAO itemDAO;

    @Resource(name = "supplierDAO")
    private SupplierDAO supplierDAO;

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
        itemDTO.setLoggedInUser( securityDAO.findByUsername(EXISTING_USERNAME_VALUE));
        itemDTO.setSupplierDTO( supplierDAO.find(1L) );

        itemDTO = itemDAO.saveOrUpdate(itemDTO);
        Assert.assertNotNull(itemDTO);
    }

    @Test
    public void testDelete() {
        ItemDTO existingItemDTO = itemDAO.find(EXISTING_ID_VALUE);
        itemDAO.delete(existingItemDTO);
    }

    @Test
    public void testUpdate() {
        ItemDTO itemDTO = itemDAO.find(EXISTING_ID_VALUE);
        itemDTO.setName("update name 2");
        itemDTO.setDescription("update desc 2");
        itemDTO.setLoggedInUser( securityDAO.findByUsername(EXISTING_USERNAME_VALUE));
        itemDTO.setSupplierDTO( supplierDAO.find(1L) );

        itemDTO = itemDAO.saveOrUpdate(itemDTO);
        Assert.assertNotNull(itemDTO);
    }

}
