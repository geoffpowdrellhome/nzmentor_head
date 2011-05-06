package com.nz.travel.mentor.util.impl;

import javax.annotation.Resource;

import net.flitech.faregate.FaregateLoginDetails;
import net.flitech.faregate.config.impl.DefaultFaregateAppConfig;
import net.flitech.faregate.service.FaregateTimeZoneFinder;
import org.springframework.stereotype.Component;

import com.nz.travel.mentor.util.TimeZoneOffsetFinder;

@Component
public class FaregateTimeZoneOffsetFinder implements TimeZoneOffsetFinder {

    @Resource
    private FaregateTimeZoneFinder finder;

    //@Resource
    //private AppConfig appConfig;

    @Resource
    DefaultFaregateAppConfig defaultFaregateAppConfig;

    @Override
    public String findTimeZoneOffset(String airport) {
        return finder.findTimeZoneOffset(createDefaultLoginDetails(), airport);
    }

    private FaregateLoginDetails createDefaultLoginDetails() {
        return new FaregateLoginDetails(defaultFaregateAppConfig.getProperty("fare.info.gws.user.id"),
                defaultFaregateAppConfig.getProperty("fare.info.gws.password"),
                defaultFaregateAppConfig.getProperty("fare.info.gws.pseudo"),
                null);
    }
}
