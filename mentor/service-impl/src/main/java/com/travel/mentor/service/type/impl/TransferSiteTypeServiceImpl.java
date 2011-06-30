package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.TransferSiteTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.TransferSiteType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("transferSiteTypeService")
@Transactional
public class TransferSiteTypeServiceImpl extends AbstractReferenceTypeService implements TransferSiteTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(TransferSiteType.FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(TransferSiteType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}
