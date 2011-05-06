package net.flitech.faregate.service;

import net.flitech.faregate.FaregateLoginDetails;

/** Finds time zone information. */
public interface FaregateTimeZoneFinder {

    String findTimeZoneOffset(FaregateLoginDetails loginDetails, String airport);
}
