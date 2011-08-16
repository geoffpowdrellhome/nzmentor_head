package com.travel.mentor.dao.base;

import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.reference.ReferenceTypeDAO;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

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
        referenceTypeDTO.setLoggedInUser( securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.saveOrUpdate(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

    @Test
    public void testDelete() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(findAllNamedQuery);
        assertRecordsReturned(referenceTypeDTOList);

        ReferenceTypeDTO existingReferenceTypeDTO = referenceTypeDTOList.get(0);

        ReferenceTypeDTO addReferenceTypeDTO = new ReferenceTypeDTO();
        BeanUtils.copyProperties(existingReferenceTypeDTO, addReferenceTypeDTO);

        addReferenceTypeDTO.setId(null);
        addReferenceTypeDTO.setDescription("temp-add");
        addReferenceTypeDTO.setName("temp-add");
        addReferenceTypeDTO.setLoggedInUser( securityDAO.findByUsername(EXISTING_USERNAME_VALUE));
        addReferenceTypeDTO = referenceTypeDAO.saveOrUpdate(addReferenceTypeDTO);

        referenceTypeDAO.delete(addReferenceTypeDTO);
    }

}
