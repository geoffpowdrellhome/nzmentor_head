package com.nz.travel.mentor.dao.dto;

import com.nz.travel.mentor.model.impl.MentorObject;


import java.util.List;
import java.util.ArrayList;

/**
 * Created by powdrelg
 * Date: 03/12/2010
 * Purpose: To construct meaningful return objects for the Fare Rules access layer
 */
public class FareRuleDTO extends MentorObject {

    private String fareRuleName;
    //private List<FareRuleItemDTO> fareRuleItems = new ArrayList<FareRuleItemDTO>();

    public FareRuleDTO() {}

    public String getFareRuleName() {
        return fareRuleName;
    }

    public void setFareRuleName(String fareRuleName) {
        this.fareRuleName = fareRuleName;
    }

//    public List<FareRuleItemDTO> getFareRuleItems() {
//        return fareRuleItems;
//    }
//
//    public void setFareRuleItems(List<FareRuleItemDTO> fareRuleItems) {
//        this.fareRuleItems = fareRuleItems;
//    }
}
