package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.Audited;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.*;

/**
 * Created by powdrelg
 * Date: 08/12/2010
 * Purpose: New JPA domain object - initially introduced for the new generic getFareRules functionality
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "FindRulesByCriteria",
                query = "SELECT DISTINCT fr FROM FareRule fr " +
                        "JOIN fr.fareRuleVersions frv " +
                        "JOIN fr.fareBases fb " +
                        "WHERE frv.fareRule.fareRuleId = fr.fareRuleId " +
                        "AND fb.fareRule.fareRuleId = fr.fareRuleId " +
                        "AND (:travelDepartureDate >= fb.validFrom "+
                        "AND (fb.validTo IS NULL OR :travelDepartureDate <= fb.validTo) "+
                        "AND rtrim(fr.airline.code) = :airlineCode " +
                        "AND (:travelDepartureDate >= frv.validFrom)  " +
                        "AND (frv.validTo IS NULL OR :travelDepartureDate <= frv.validTo) " +
                        "AND ( (:fareBasisCode IS NULL) OR  (rtrim(fb.fareBasisCode) = :fareBasisCode) "+
                        "AND ( (:classCode IS NULL) OR (rtrim(fb.fareClass.classCode) = :classCode)) "+
                        " )) ")
})
@Table(name = "fare_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FareRule extends Audited {

    // Literals to define the Named Queries.
    public static final String FIND_FARE_RULES_BY_CRITERIA_NAMED_QUERY = "FindRulesByCriteria";

    @Id
    @Column(name = "fare_rule_id", nullable = false)
    private Long fareRuleId;

//    @ManyToOne
//    @JoinColumn(name = "airline_code", referencedColumnName = "code")
//    private Airline airline;
//
//    @Column(name = "fare_rule_name")
//    private String fareRuleName;
//
//    @OneToMany(mappedBy = "fareRule", targetEntity = FareRuleVersion.class, fetch = FetchType.LAZY)
//    private List<FareRuleVersion> fareRuleVersions = new ArrayList<FareRuleVersion>();
//
//    @OneToMany(mappedBy = "fareRule", targetEntity = FareBase.class, fetch = FetchType.LAZY)
//    private List<FareBase> fareBases = new ArrayList<FareBase>();

    public Long getFareRuleId() {
        return fareRuleId;
    }

    public void setFareRuleId(Long fareRuleId) {
        this.fareRuleId = fareRuleId;
    }


}
