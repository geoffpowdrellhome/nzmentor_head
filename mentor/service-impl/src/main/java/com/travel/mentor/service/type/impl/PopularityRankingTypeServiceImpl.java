package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.PopularityRankingTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.PopularityRankingType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("popularityRankingTypeService")
@Transactional
public class PopularityRankingTypeServiceImpl extends AbstractReferenceTypeService implements PopularityRankingTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(PopularityRankingType.FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(PopularityRankingType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}

