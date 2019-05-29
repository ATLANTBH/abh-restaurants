package com.atlantbh.devdays.demo.abh.restaurants.utils.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

  /**
   * Advances a date object by specific amount in a time type specified by field. Available types
   * can be seen in {@link Calendar} class, ie {@link Calendar#MILLISECOND}
   *
   * @param date Date object to advance.
   * @param field Field to advance ie {@link Calendar#MILLISECOND} or {@link Calendar#HOUR_OF_DAY}
   * @param amount Amount of time to advance.
   * @return New date.
   */
  public static Date advanceDateTime(Date date, int field, int amount) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(field, amount);
    return calendar.getTime();
  }

  /**
   * Given a date return a new date which has hours set to start of that day.
   *
   * @param baseDay Base date
   * @return New date.
   */
  public static Date startOfDay(Date baseDay) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(baseDay);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * Given a date return a new date which has hours set to start of that day.
   *
   * @param baseDay Base date
   * @return New date.
   */
  public static Date endOfDay(Date baseDay) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(baseDay);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    return calendar.getTime();
  }
}
