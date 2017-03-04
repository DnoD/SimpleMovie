package com.dnod.simplemovie.utils;

import com.dnod.simplemovie.R;
import com.dnod.simplemovie.SimpleMovieController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * This is a Utils class with necessary time formats
 */
public final class TimeUtils {
    public static final String ENDPOINT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String RELEASE_DATE_FORMAT = "dd MMM yyyy";

    public static String formatMovieDuration(int durationInMinutes) {
        long hoursToFinish = TimeUnit.MINUTES.toHours(durationInMinutes);
        if (hoursToFinish > 0) {
            durationInMinutes -= TimeUnit.HOURS.toMinutes(hoursToFinish);
            return SimpleMovieController.getInstance()
                    .getString(R.string.order_timer_hours_format,
                            hoursToFinish, durationInMinutes);
        }
        return SimpleMovieController.getInstance().getResources()
                .getString(R.string.order_timer_minutes_format, durationInMinutes);
    }

    public static long getEndpointDate(String dateString) {
        return getDate(dateString, ENDPOINT_DATE_FORMAT);
    }

    public static String formatReleaseDate(long date) {
        return format(date, RELEASE_DATE_FORMAT);
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
