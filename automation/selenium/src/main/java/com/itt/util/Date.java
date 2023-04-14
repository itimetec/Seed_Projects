package com.itt.util;

import java.text.ParseException;

import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

    public enum Format {
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY_DD_MM("yyyy-dd-MM"),
        DD_MM_YYYY("dd-MM-yyyy"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd hh:mm:ss"),
        DD_MM_YYYY_HH_MM_SS("dd-MM-yyyy hh:mm:ss"),
        DD_MMM_YYYY_HH_MM_SS("dd-MMM-yyyy hh:mm:ss");

        private String format;

        Format(String format) {
            this.format = format;
        }

        @Override
        public String toString() {
            return this.format;
        }
    }

    public static String addOneDay(String date) {
        return java.time.LocalDate.parse(date).plusDays(1).toString();
    }

    public static String removeOneDay(String date) {
        return java.time.LocalDate.parse(date).minusDays(1).toString();
    }

    public static String updateHoursInDate(Format format, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        cal.add(Calendar.HOUR, hour);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayDate(Format format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        return dateFormat.format(new java.util.Date());
    }

    public static String getYesterdayDate(Format format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    public static String getCurrentMonthOfPreviousYear(Format format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return dateFormat.format(cal.getTime());
    }

    public static String getCurrentYear(Format format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar cal = Calendar.getInstance();
        return Integer.toString(cal.get(Calendar.YEAR));
    }

    public static String getTomorrowDate(Format format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return dateFormat.format(cal.getTime());
    }

    public static String getLastDateOfMonth(Format format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Note: This function does not include endDate in calculation
     *
     * @param startDate
     * @param endDate
     * @return date between start_date and end_date
     */
    public static long getNumberOfBusinessDays(LocalDate startDate, LocalDate endDate) {
        long count = 0;
        LocalDate date = startDate;
        while (date.isBefore(endDate)) {
            if (!(date.getDayOfWeek() == DateTimeConstants.SATURDAY || date.getDayOfWeek() == DateTimeConstants.SUNDAY)) {
                count++;
            }
            date = date.plusDays(1);
        }
        return count;
    }

    public static String getFutureDate(Format format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return dateFormat.format(cal.getTime());
    }

    public static long getNumberOfDaysInBetween(LocalDate startDate, LocalDate endDate) {
        return Days.daysBetween(startDate, endDate).getDays();
    }

    public static String addMonths(Format format, int monthCount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthCount);
        return dateFormat.format(calendar.getTime());
    }

    public static String addDays(Format format, int dayCount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayCount);
        return dateFormat.format(calendar.getTime());
    }

    public static String removeMonths(Format format, int monthCount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -monthCount);
        return dateFormat.format(calendar.getTime());
    }

    public static java.time.Month getCurrentMonth() {
        return java.time.LocalDate.now().getMonth();
    }

    public static boolean isValidDate(Format format, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static LocalDate convertDateToSpecifiedFormat(Format format, String date) {
        return DateTimeFormat.forPattern(String.valueOf(Date.Format.YYYY_MM_DD)).parseLocalDate(date);
    }

    public static String getOnlyYearFromDate(String date) {
        return (Date.convertDateToSpecifiedFormat(Date.Format.YYYY_MM_DD, date)).toString().substring(0, 4);
    }

    public static String getOnlyMonthFromDate(String date) {
        return (Date.convertDateToSpecifiedFormat(Date.Format.YYYY_MM_DD, date)).toString().substring(5, 7);
    }

}
