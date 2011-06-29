package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class AccommodationSiteTypeServiceTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "accommodationSiteTypeService")
    private AccommodationSiteTypeService accommodationSiteTypeService;

    @Test
    public void testFindAll() {
        List<ReferenceTypeDTO> referenceTypeDTOList = accommodationSiteTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);
    }

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc");
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO(userService.find(EXISTING_USERNAME_VALUE));
        referenceTypeDTO = accommodationSiteTypeService.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Test
    public void testUpdate() {
        List<ReferenceTypeDTO> referenceTypeDTOList = accommodationSiteTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDTO.setName("update2");
        referenceTypeDTO.setDescription("update2");
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userService.find(EXISTING_USERNAME_VALUE) );

        referenceTypeDTO = accommodationSiteTypeService.update(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Test
    public void testDelete() {
        List<ReferenceTypeDTO> referenceTypeDTOList = accommodationSiteTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.

        accommodationSiteTypeService.delete(referenceTypeDTO);
    }

}
