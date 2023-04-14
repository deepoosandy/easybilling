package com.sanapp.sms.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtility {
    //dd-MM-yyyy HH:mm:ss
    private final static String DATETIMEFORMAT = "dd-MM-yyyy HH:mm:ss";
    public final static String GETDATEFORMAT = "yyyy-MM-dd'T'HH:mm";
    public final static String DATEFORMAT="yyyy-MM-dd";//2023-04-07

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIMEFORMAT);
    private static DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern(DATEFORMAT);

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
    public static String localDateToString(LocalDate dateTime) {
        return dateTime.format(dateformatter);

    }

    public static LocalDate stringToLocaldate(String dateTime) {
        return LocalDate.parse(dateTime,dateformatter);

    }
}
