package com.travel.mentor.base;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.base.ReferenceTypeService;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public abstract class AbstractSpringServiceReferenceTypeTestCase extends AbstractSpringServiceImplTestCase {

    protected ReferenceTypeService referenceTypeService=null;

    @Test
    public void testFindAll() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);
    }

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc");
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO(userService.find(EXISTING_USERNAME_VALUE));
        referenceTypeDTO = referenceTypeService.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Test
    public void testUpdate() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDTO.setName("update2");
        referenceTypeDTO.setDescription("update2");
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userService.find(EXISTING_USERNAME_VALUE) );

        referenceTypeDTO = referenceTypeService.update(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Test
    public void testDelete() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeService.findAll();
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.

        referenceTypeService.delete(referenceTypeDTO);
    }

}
