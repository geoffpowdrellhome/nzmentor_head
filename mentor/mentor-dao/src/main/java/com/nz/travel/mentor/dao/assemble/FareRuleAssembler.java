package com.nz.travel.mentor.dao.assemble;

import com.nz.travel.mentor.dao.dto.FareRuleDTO;
import com.nz.travel.mentor.model.impl.FareRule;

import java.util.List;

/**
 * Created by powdrelg
 * Date: 03/12/2010
 * Purpose: To construct meaningful return objects for the Fare Rules access layer
 */
public interface FareRuleAssembler {

    List<FareRuleDTO> assembleToFareRuleDTOList(List<FareRule> fareRuleList);

}

