package com.travel.mentor.core;

/**
 * Constant values
 */
public interface Constants {

    String SPACE = " ";
    String PERIOD = ".";
    String ASTERISK = "*";
    String BLANK = "";
    String DASH = "-";
    String COLON = ":";
    String COMMA = ",";
    String TAB = "\t";
    String FARE_KEY_DELIMITER = "#"; // note: avoid chars with special meaning in regex, eg .\|+

    String MS = "ms";
    String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    String DATE_FORMAT = "dd/MM/yyyy";

    String TIME_FORMAT = "HH:mm";

    String DATETIME_FORMAT = DATE_FORMAT + SPACE + TIME_FORMAT;

    String GNE_DATE_FORMAT = "yyyy-MM-dd";

    String DATE_FORMAT_XSD = "yyyy-MM-dd";
    String TIME_FORMAT_XSD = "HH:mm:ss";

}
