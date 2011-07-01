package com.travel.mentor.service.type.base;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;

import javax.annotation.Resource;
import java.util.List;

public abstract class AbstractReferenceTypeService implements ReferenceTypeService {

    @Resource(name="referenceTypeDAO")
    protected ReferenceTypeDAO referenceTypeDAO;

    public abstract List<ReferenceTypeDTO> findAll();

    public abstract ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO);

    @Override
    public ReferenceTypeDTO update(ReferenceTypeDTO referenceTypeDTO) {
        return referenceTypeDAO.update(referenceTypeDTO);
    }

    @Override
    public void delete(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDAO.delete(referenceTypeDTO);
    }

    @Override
    public ReferenceTypeDTO find(ReferenceTypeDTO referenceTypeDTO) {
        return referenceTypeDAO.find(referenceTypeDTO);
    }

}
