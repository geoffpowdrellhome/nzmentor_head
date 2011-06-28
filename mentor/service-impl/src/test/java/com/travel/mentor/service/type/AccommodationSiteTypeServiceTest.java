package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.AccommodationSiteType;
import junit.framework.Assert;

import javax.annotation.Resource;
import java.util.List;

public class AccommodationSiteTypeServiceTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "accommodationSiteTypeService")
    private AccommodationSiteTypeService accommodationSiteTypeService;

    public void testFindAll() {
        List<ReferenceTypeDTO> referenceTypeDTOList = accommodationSiteTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);
    }

    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("Dive-Hotel", "Dive-Hotel", AccommodationSiteType.class);
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO(userService.find(EXISTING_USERNAME_VALUE));
        referenceTypeDTO = accommodationSiteTypeService.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Override
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

    @Override
    public void testDelete() {
        List<ReferenceTypeDTO> referenceTypeDTOList = accommodationSiteTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.

        accommodationSiteTypeService.delete(referenceTypeDTO);
    }

}
