package com.atlantbh.devdays.demo.abh.restaurants.utils.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date-time related utility class.
 *
 * @author Kenan Klisura
 */
public final class DateTimeUtils {
    private static final String DEFAULT_DATE_PATTERN = "EEEE, MMM dd, yyyy";
    private static final String DEFAULT_TIME_PATTERN = "h:mm a";

    // TODO(kklisura): Use this somewhere.
    private static final String DEFAULT_SHORT_TIME_PATTERN = "HH:mm";
    private static final String DEFAULT_LONG_TIME_PATTERN = "HH:mm:ss";

    /**
     * Formats a date using a {@link #DEFAULT_DATE_PATTERN}.
     *
     * @param date Date to format.
     * @return Formatted time.
     */
    public static String formatAsDate(Date date) {
        return formatDate(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * Formats a date using a {@link #DEFAULT_TIME_PATTERN}.
     *
     * @param date Date to format.
     * @return Formatted time.
     */
    public static String formatAsTime(Date date) {
        return formatDate(date, DEFAULT_TIME_PATTERN);
    }

    /**
     * Formats a date/time given a format.
     *
     * @param date Date to format.
     * @param format Format.
     * @return Formatted date/time string.
     */
    public static String formatDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
}
