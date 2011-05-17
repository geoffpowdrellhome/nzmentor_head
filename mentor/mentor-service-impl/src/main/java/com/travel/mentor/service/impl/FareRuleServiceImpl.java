package com.travel.mentor.service.impl;

import com.travel.mentor.dao.dto.FareRuleDTO;

import com.travel.mentor.service.FareRuleService;
import org.joda.time.DateTime;

import java.util.List;

public class FareRuleServiceImpl implements FareRuleService {

    @Override
    public List<FareRuleDTO> findFareRulesByCriteria(String airlineCode, DateTime travelDepartureDate, String fareBasisCode, String classCode) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
