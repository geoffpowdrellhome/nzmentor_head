package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.AccommodationSiteType;
import junit.framework.Assert;

import java.util.List;

public class AccommodationSiteTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase {

    @Override
    public void testFindAll() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        assertRecordsReturned(referenceTypeDTOList);
    }

    @Override
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("Dive-Hotel", "Dive-Hotel", AccommodationSiteType.class);
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Override
    public void testUpdate() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDTO.setName("update2");
        referenceTypeDTO.setDescription("update2");
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.update(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Override
    public void testDelete() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDAO.delete(referenceTypeDTO);
    }

}
