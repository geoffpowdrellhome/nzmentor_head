package com.travel.mentor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.travel.mentor.core.Constants;
import org.apache.log4j.Logger;

/**
 * This class provides some Date-related utility methods.
 *
 * @author dyoungs
 */
public final class DateUtil {

    private static final Logger LOGGER = Logger.getLogger(DateUtil.class);
    private static final String GMT = "GMT";
    private static final String COULD_NOT_CONVERT = "Could not convert (";
    private static final String USING_PATTERN = ") using pattern (";
    private static final String THIS_DATE_SHOULD_HAVE_BEEN_VALIDATED_EARLIER = "). This date should have been validated earlier";

    private DateUtil() {
    }

    public static Date stringToDate(String dateString, String pattern, TimeZone timeZone) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        if (timeZone != null) {
            formatter.setTimeZone(timeZone);
        }
        Date convertedDate = null;
        try {
            convertedDate = formatter.parse(dateString);
        } catch (ParseException pe) {
            throw new Exception(COULD_NOT_CONVERT + dateString + USING_PATTERN + formatter.toPattern()
                    + THIS_DATE_SHOULD_HAVE_BEEN_VALIDATED_EARLIER, pe);
        }
        return convertedDate;
    }

    public static Date stringToDate(String dateString, String pattern) throws Exception {
        return stringToDate(dateString, pattern, null);
    }

    public static String createTimeString(Calendar date, int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.getTime());
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        return new SimpleDateFormat(Constants.DATETIME_FORMAT).format(cal.getTime());
    }

    public static String dateToString(Date date, String pattern) {
        return dateToString(date, pattern, null);
    }

    public static String dateToString(Date date, String pattern, TimeZone timeZone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }
        return dateFormat.format(date);
    }

    public static Calendar createCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Calendar createGMTCalendar(Date date) {
        Calendar cal = createCalendar(date);
        cal.setTimeZone(TimeZone.getTimeZone(GMT));
        return cal;
    }

    public static Calendar clear(Calendar calendar) {
        calendar.clear();
        return calendar;
    }

    public static Calendar add(Calendar calendar, int field, int value) {
        calendar.add(field, value);
        return calendar;
    }

    public static Calendar addDays(Calendar calendar, int days) {
        return add(calendar, Calendar.DATE, days);
    }

    public static Calendar addMonths(Calendar calendar, int months) {
        return add(calendar, Calendar.MONTH, months);
    }

    public static Calendar addYears(Calendar calendar, int years) {
        return add(calendar, Calendar.YEAR, years);
    }

    public static Calendar set(Calendar calendar, int field, int value) {
        calendar.set(field, value);
        return calendar;
    }

    // TODO: test setTime
    public static Calendar setTime(Calendar calendar, int hour, int minute, int second, int millisecond) {
        setHours(calendar, hour);
        setMinutes(calendar, minute);
        setSeconds(calendar, second);
        setMilliseconds(calendar, millisecond);
        return calendar;
    }

    public static Calendar setHours(Calendar calendar, int hours) {
        return set(calendar, Calendar.HOUR_OF_DAY, hours);
    }

    public static Calendar setMinutes(Calendar calendar, int minutes) {
        return set(calendar, Calendar.MINUTE, minutes);
    }

    public static Calendar setSeconds(Calendar calendar, int seconds) {
        return set(calendar, Calendar.SECOND, seconds);
    }

    public static Calendar setMilliseconds(Calendar calendar, int milliseconds) {
        return set(calendar, Calendar.MILLISECOND, milliseconds);
    }

    /**
     * Had to do make a fresh calendar to make the test work in JetstarCommitTranslatorUnitTest.
     */
    public static Calendar setLastDayOfMonth(Calendar calendar) {
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Calendar result = Calendar.getInstance(calendar.getTimeZone());
        result.setTime(calendar.getTime());
        return result;
    }

    public static String formatCalendar(Calendar calendar, String pattern) {
        return formatCalendar(calendar, pattern, calendar.getTimeZone());
    }

    private static String formatCalendar(Calendar calendar, String pattern, TimeZone timezone) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(timezone);
        return formatter.format(calendar.getTime());
    }


    /**
     * returns an XMLGregorianCalendar, or null if something went wrong
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(String dateString, String pattern) {
        return toXMLGregorianCalendar(dateString, pattern, null);
    }

    /**
     * returns an XMLGregorianCalendar, or null if something went wrong
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(String dateString, String pattern, TimeZone timeZone) {

        XMLGregorianCalendar xmlCal = null;
        try {
            Date date = stringToDate(dateString, pattern, timeZone);
            Calendar cal = createCalendar(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(year, month, day,
                    DatatypeConstants.FIELD_UNDEFINED);
        } catch (Exception e) {
            LOGGER.error("Error creating a new XMLGregorianCalendar from " + dateString + " with format " + pattern
                    + ".\n" + e);
        }
        return xmlCal;
    }

    /**
     * See javadoc for SimpleDateFormat regarding abbreviated date pattern: SimpleDateFormat must interpret the
     * abbreviated year relative to some century. It does this by adjusting dates to be within 80 years before and 20
     * years after the time the SimpleDateFormat instance is created.
     */
    public static String padYear(String year) throws Exception {
        if (year.length() == 4) return year;
        String result = null;
        String pattern = "yy";
        try {
            if (year.length() != 2) {
                throw new ParseException("Year to pad must be exactly two characters long. The value passed in was: "
                        + year, 2);
            }
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            Date date = formatter.parse(year);
            Calendar cal = createCalendar(date);
            int paddedYear = cal.get(Calendar.YEAR);
            result = "" + paddedYear;
        } catch (ParseException e) {
            throw new Exception("Unable to pad year: " + year + ". This date should have been validated earlier.", e);
        }
        return result;
    }

    /**
     * returns a GregorianCalendar, passing in a LocalDate
     */
    public static GregorianCalendar toGregorianCalendar(org.joda.time.LocalDate localDate) {
        GregorianCalendar cal = new GregorianCalendar(localDate.getYear(),
                localDate.getMonthOfYear(),
                localDate.getDayOfMonth());

        return cal;
    }

}

