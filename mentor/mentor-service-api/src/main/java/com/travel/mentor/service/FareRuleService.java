package com.travel.mentor.service;

import com.travel.mentor.dao.dto.FareRuleDTO;
import org.joda.time.DateTime;

import java.util.List;

public interface FareRuleService {

    List<FareRuleDTO> findFareRulesByCriteria(String airlineCode,
                                              DateTime travelDepartureDate,
                                              String fareBasisCode,
                                              String classCode);

}
