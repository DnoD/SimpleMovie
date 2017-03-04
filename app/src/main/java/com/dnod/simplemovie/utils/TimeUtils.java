package com.dnod.simplemovie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This is a Utils class with necessary time formats
 */
public final class TimeUtils {
    public static final String ENDPOINT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static long getEndpointDate(String dateString) {
        return getDate(dateString, ENDPOINT_DATE_FORMAT);
    }

    public static String formatEndpointDate(long date) {
        return format(date, ENDPOINT_DATE_FORMAT);
    }

    private static String format(long time, String pattern) {
        return format(time, pattern, null);
    }

    private static String format(long time, String pattern, TimeZone timeZone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return dateFormat.format(calendar.getTime());
    }

    private static long getDate(String date, String format) {
        return getDate(date, format, Locale.getDefault());
    }

    private static long getDate(String date, String format, Locale locale) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
        try {
            return dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
