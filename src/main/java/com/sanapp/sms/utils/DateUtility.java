package com.sanapp.sms.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtility {
    //dd-MM-yyyy HH:mm:ss
    private final static String dateFormat = "dd-MM-yyyy HH:mm:ss";
    public final static String GETDATEFORMAT = "yyyy-MM-dd'T'HH:mm";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

    public static LocalDateTime todaysDate() {

        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.parse(formatter.format(now), formatter);

    }

    public static LocalDateTime thruDate() {
        return LocalDateTime.parse("12-12-9999 02:23:12", formatter);

    }

    public static String dateInString(LocalDateTime dateTime) {
        return formatter.format(dateTime);
    }

    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern(GETDATEFORMAT);
    }

    public static LocalDateTime stringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = getDateFormatter();
        return LocalDateTime.parse(dateTime, formatter);

    }

    public static String localDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = getDateFormatter();
        return dateTime.format(formatter);

    }
}
