package com.xiang.api.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
    public static final String DATE_PARTEN_YMDHMSS = "yyyyMMddHHmmssSSS";
    public static final String DATE_PARTEN_YMDHMSS_NORMAL = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String DATE_PARTEN_YMDHMSS_DOTA = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_PARTEN_YYMMDD = "yy-MM-dd";
    public static final String DATE_PARTEN_YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_PARTEN_YYYYMM = "yyyy-MM";
    public static final String DATE_PARTEN_YYYY = "yyyy";
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public DateUtil() {
    }

    public static Date safeParseDate(String dateStr) {
        try {
            return getFormat("yyyy-MM-dd HH:mm:ss SSS").parse(dateStr);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date safeParseDate(String pattern, String dateStr) {
        try {
            return getFormat(pattern).parse(dateStr);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String safeFormatDate(Date date) {
        try {
            return getFormat("yyyy-MM-dd HH:mm:ss SSS").format(date);
        } catch (Exception var2) {
            return "";
        }
    }

    public static String safeFormatDate(String pattern, Date date) {
        try {
            return getFormat(pattern).format(date);
        } catch (Exception var3) {
            return "";
        }
    }

    private static SimpleDateFormat getFormat(String pattern) {
        ((SimpleDateFormat)threadLocal.get()).applyPattern(pattern);
        return (SimpleDateFormat)threadLocal.get();
    }

    public static Date getDateNSecondsAfter(Date dt, int seconds) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt);
        calendar.add(13, seconds);
        return calendar.getTime();
    }

    public static Date getDateNMinutesAfter(Date dt, int minutes) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt);
        calendar.add(12, minutes);
        return calendar.getTime();
    }

    public static Date getDateNHoursAfter(Date dt, int hours) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt);
        calendar.add(10, hours);
        return calendar.getTime();
    }

    public static Date getDateNDaysAfter(Date dt, int ndays) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt);
        calendar.add(6, ndays);
        return calendar.getTime();
    }

    public static Date getDateNMonthAfter(Date dt, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt);
        calendar.add(2, month);
        return calendar.getTime();
    }

    public static Date getDateNYearsAfter(Date dt, int year) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt);
        calendar.add(1, year);
        return calendar.getTime();
    }

    public static Date getDateBegin(Date dt) {
        String dtStr = safeFormatDate("yyyy-MM-dd", dt);
        return safeParseDate("yyyy-MM-dd", dtStr);
    }

    public static List<Date> getIntervalDays(String startStr, String endStr) {
        Date start = safeParseDate("yyyy-MM-dd", startStr);
        Date end = safeParseDate("yyyy-MM-dd", endStr);
        return getIntervalDays(start, end);
    }

    public static int daysBetween(Date dateBegin, Date dateEnd) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long begin = df.parse(df.format(dateBegin)).getTime();
        long end = df.parse(df.format(dateEnd)).getTime();
        return (int)((end - begin) / 86400000L);
    }

    public static List<Date> getIntervalHours(Date start, Date end) {
        String startDString = safeFormatDate("yyyy-MM-dd HH", start);
        String endDString = safeFormatDate("yyyy-MM-dd HH", end);
        start = safeParseDate("yyyy-MM-dd HH", startDString);
        end = safeParseDate("yyyy-MM-dd HH", endDString);
        long millonTimes = end.getTime() - start.getTime();
        long days = millonTimes / 3600000L;
        List<Date> result = new ArrayList();
        result.add(start);
        Calendar calendar = new GregorianCalendar();

        for(int i = 1; (long)i <= days; ++i) {
            calendar.setTime((Date)result.get(i - 1));
            calendar.add(10, 1);
            result.add(calendar.getTime());
        }

        return result;
    }

    public static List<Date> getIntervalDays(Date start, Date end) {
        String startDString = safeFormatDate("yyyy-MM-dd", start);
        String endDString = safeFormatDate("yyyy-MM-dd", end);
        start = safeParseDate("yyyy-MM-dd", startDString);
        end = safeParseDate("yyyy-MM-dd", endDString);
        long millonTimes = end.getTime() - start.getTime();
        long days = millonTimes / 86400000L;
        List<Date> result = new ArrayList();
        result.add(start);
        Calendar calendar = new GregorianCalendar();

        for(int i = 1; (long)i <= days; ++i) {
            calendar.setTime((Date)result.get(i - 1));
            calendar.add(6, 1);
            result.add(calendar.getTime());
        }

        return result;
    }

    public static List<Date> getIntervalMonths(Date start, Date end) {
        String dtFormat = "yyyy-MM-01";
        String startDString = safeFormatDate(dtFormat, start);
        String endDString = safeFormatDate(dtFormat, end);
        start = safeParseDate(dtFormat, startDString);
        end = safeParseDate(dtFormat, endDString);
        List<Date> result = new ArrayList();
        GregorianCalendar calendar = new GregorianCalendar();

        do {
            result.add(start);
            calendar.setTime(start);
            calendar.add(2, 1);
            start = calendar.getTime();
        } while(start.getTime() <= end.getTime());

        return result;
    }

    public static List<Date> getIntervalMonths(String startStr, String endStr) {
        Date start = safeParseDate("yyyy-MM-dd", startStr);
        Date end = safeParseDate("yyyy-MM-dd", endStr);
        return getIntervalMonths(start, end);
    }

    public static List<Date> getIntervalYears(Date start, Date end) {
        String dtFormat = "yyyy-01-01";
        String startDString = safeFormatDate(dtFormat, start);
        String endDString = safeFormatDate(dtFormat, end);
        start = safeParseDate(dtFormat, startDString);
        end = safeParseDate(dtFormat, endDString);
        List<Date> result = new ArrayList();
        GregorianCalendar calendar = new GregorianCalendar();

        do {
            result.add(start);
            calendar.setTime(start);
            calendar.add(1, 1);
            start = calendar.getTime();
        } while(start.getTime() <= end.getTime());

        return result;
    }

    public static boolean areSameDay(long d1, long d2) {
        if (d1 > 0L && d2 > 0L) {
            Calendar calDateA = Calendar.getInstance();
            calDateA.setTime(new Date(d1));
            Calendar calDateB = Calendar.getInstance();
            calDateB.setTime(new Date(d2));
            return calDateA.get(1) == calDateB.get(1) && calDateA.get(2) == calDateB.get(2) && calDateA.get(5) == calDateB.get(5);
        } else {
            return false;
        }
    }

    public static boolean areSameDay(Date dateA, Date dateB) {
        if (dateA != null && dateB != null) {
            Calendar calDateA = Calendar.getInstance();
            calDateA.setTime(dateA);
            Calendar calDateB = Calendar.getInstance();
            calDateB.setTime(dateB);
            return calDateA.get(1) == calDateB.get(1) && calDateA.get(2) == calDateB.get(2) && calDateA.get(5) == calDateB.get(5);
        } else {
            return false;
        }
    }

    public static int getMonth(Date dt) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dt);
        return calDateA.get(2) + 1;
    }

    public static int getDay(Date dt) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dt);
        return calDateA.get(5);
    }

    public static int getDayOfWeek(Date dt) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dt);
        return calDateA.get(7);
    }

    public static int getYear(Date dt) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dt);
        return calDateA.get(1);
    }

    public static int getHour(Date dt) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dt);
        return calDateA.get(11);
    }

    public static int getMinue(Date dt) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dt);
        return calDateA.get(12);
    }

    public static int getSecond(Date dt) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dt);
        return calDateA.get(13);
    }

    public static char getLetterFromNum(int num) {
        if (num >= 0 && num <= 31) {
            return num < 26 ? (char)(65 + num) : (char)(97 + (num - 26));
        } else {
            return '\u0000';
        }
    }

    public String getStrByDateDMHS(Date dt) {
        StringBuffer idBuffer = new StringBuffer();
        idBuffer.append(getLetterFromNum(getDay(dt)));
        idBuffer.append(getLetterFromNum(getHour(dt)));
        idBuffer.append(getCharFromNum(getMinue(dt)));
        idBuffer.append(getCharFromNum(getSecond(dt)));
        return idBuffer.toString();
    }

    public static int getNumFromLetter(char numChar) {
        if (numChar < 'A') {
            return -1;
        } else if (numChar < '[') {
            return numChar - 65;
        } else if (numChar < 'a') {
            return -1;
        } else {
            return numChar < '{' ? numChar - 97 + 26 : -1;
        }
    }

    public static char getCharFromNum(int num) {
        if (num >= 0 && num <= 60) {
            if (num < 10) {
                return (char)(48 + num);
            } else if (num < 36) {
                return (char)(65 + (num - 10));
            } else {
                return num <= 60 ? (char)(97 + (num - 36)) : '\u0000';
            }
        } else {
            return '\u0000';
        }
    }

    public static int getNumFromChar(char timeAscii) {
        if (timeAscii < '0') {
            return -1;
        } else if (timeAscii < ':') {
            return timeAscii - 48;
        } else if (timeAscii < 'A') {
            return -1;
        } else if (timeAscii < '[') {
            return timeAscii - 65 + 10;
        } else if (timeAscii < 'a') {
            return -1;
        } else {
            return timeAscii < '{' ? timeAscii - 97 + 36 : -1;
        }
    }

    public static long getTimeMs(String timeStr) {
        Matcher m = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{2}").matcher(timeStr);
        if (m.find()) {
            try {
                String ms = timeStr.substring(9);
                String hour = timeStr.substring(0, 2);
                String second = timeStr.substring(6, 8);
                String min = timeStr.substring(3, 5);
                return Long.valueOf(ms) + (long)(Integer.valueOf(second) * 1000) + (long)(Integer.valueOf(min) * '\uea60') + (long)(Integer.valueOf(hour) * 3600000);
            } catch (Exception var6) {
                return 0L;
            }
        } else {
            return -1L;
        }
    }

    public static long getTimeMs_V2(String timeStr) {
        Matcher m = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{2}").matcher(timeStr);
        if (m.find()) {
            Date sDt = safeParseDate("yyyy-MM-dd", "1985-01-06");
            Date eDt = safeParseDate("yyyy-MM-dd HH:mm:ss.SS", "1985-01-06 " + timeStr);
            return eDt.getTime() - sDt.getTime();
        } else {
            return -1L;
        }
    }

    public static int getDivValue(Date dt, int type) {
        if (type == 1) {
            int yearNow = getYear(new Date());
            int yearIn = getYear(dt);
            return yearNow - yearIn;
        } else {
            return 0;
        }
    }
}
