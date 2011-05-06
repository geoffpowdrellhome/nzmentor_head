package com.nz.travel.mentor.util;


public interface TimeZoneOffsetFinder {

    /**
     * Finds the time zone for an airport code, rounded to the nearest half-hour to avoid clock sync issues.
     * @return the formatted timezone offset, e.g. "+10", "-4" etc
     */
    String findTimeZoneOffset(String airport);
}
