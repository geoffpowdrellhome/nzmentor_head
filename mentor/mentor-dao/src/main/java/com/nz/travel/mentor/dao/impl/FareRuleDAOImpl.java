package com.nz.travel.mentor.dao.impl;

import com.nz.travel.mentor.dao.FareRuleDAO;
import com.nz.travel.mentor.dao.assemble.FareRuleAssembler;
import com.nz.travel.mentor.dao.base.AbstractFaregateDAO;
import com.nz.travel.mentor.model.impl.FareRule;

import com.nz.travel.mentor.dao.dto.FareRuleDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by powdrelg Date: 08/12/2010 *
 * @see: net.flitech.faregate.dao.FareRuleDao
 */
@Repository("fareRuleDAO")
public class FareRuleDAOImpl extends AbstractFaregateDAO implements FareRuleDAO {

    @Resource
    private FareRuleAssembler fareRuleAssembler;

    public List<FareRuleDTO> findFareRulesByCriteria(String airlineCode, // mandatory
            DateTime travelDepartureDate, // mandatory
            String fareBasisCode, // optional
            String classCode) { // optional

        Assert.notNull(airlineCode);
        Assert.notNull(travelDepartureDate);

        Query query = em.createNamedQuery(FareRule.FIND_FARE_RULES_BY_CRITERIA_NAMED_QUERY);
        query.setParameter("airlineCode", airlineCode);
        query.setParameter("travelDepartureDate", travelDepartureDate);
        query.setParameter("fareBasisCode", (fareBasisCode != null) ? fareBasisCode : null);
        query.setParameter("classCode", (classCode != null) ? classCode : null);

        List<FareRule> fareRuleDomainList = query.getResultList();
        
        return fareRuleAssembler.assembleToFareRuleDTOList(fareRuleDomainList);
    }
}
