package com.travel.mentor.dao.base;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public abstract class AbstractReferenceTypeDAOTestCase extends AbstractMentorDAOImplTestCase {

    @Resource(name = "referenceTypeDAO")
    protected ReferenceTypeDAO referenceTypeDAO;

    protected String findAllNamedQuery=null;

    @Test
    public void testFindAll() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(findAllNamedQuery);
        assertRecordsReturned(referenceTypeDTOList);
    }

    @Test
    public void testUpdate() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(findAllNamedQuery);
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDTO.setName("update2");
        referenceTypeDTO.setDescription("update2");
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.update(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Test
    public void testDelete() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(findAllNamedQuery);
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO referenceTypeDTO = referenceTypeDTOList.get(0); // get the first one.
        referenceTypeDAO.delete(referenceTypeDTO);
    }

}
