package com.nz.travel.mentor.dao;

import com.nz.travel.mentor.dao.dto.FareRuleDTO;
import org.joda.time.DateTime;
import java.util.List;

/**
 * Created by powdrelg
 * Date: 03/12/2010
 * Purpose: Obtain the fare rules by criteria
 */
public interface FareRuleDAO {

    List<FareRuleDTO> findFareRulesByCriteria(String airlineCode,
                                              DateTime travelDepartureDate,
                                              String fareBasisCode,
                                              String classCode);
}
