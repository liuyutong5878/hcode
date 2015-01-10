package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil {

    public static final String F_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String F_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String F_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 时间转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 时间(long类型)转字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String getString(long time, String format) {
        return getString(new Date(time), format);
    }

    /**
     * 取指定时间前后的第N天（正数为后，负数为前）
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getAfterDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 取指定时间前后的第N个月（正数为后，负数为前）
     *
     * @param date
     * @param months
     * @return
     */
    public static Date getAfterMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * 由字符串取日期
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date d = sdf.parse(date);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 由字符串取long类型时间
     *
     * @param date
     * @param format
     * @return
     */
    public static long getTime(String date, String format) {

        long time = 0L;
        try {
            time = getDate(date, format).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 指定时间内，取YYYY的列表
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getYYYYList(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int startY = calendar.get(Calendar.YEAR);
        calendar.setTime(end);
        int endY = calendar.get(Calendar.YEAR);

        List<String> list = new ArrayList<>();
        for (int y = startY; y <= endY; y++) {
            list.add(String.valueOf(y));
        }

        return list;
    }

    /**
     * 指定的时间内取YYYYMM的列表
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getYYYYMMList(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.set(Calendar.DAY_OF_MONTH, 1);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            if (calendar.after(endCalendar)) {
                break;
            }
            int month = calendar.get(Calendar.MONTH) + 1;
            if (month > 9) {
                list.add(String.valueOf(calendar.get(Calendar.YEAR) + String.valueOf(month)));
            } else {
                list.add(String.valueOf(calendar.get(Calendar.YEAR) + "0" + String.valueOf(month)));
            }
            calendar.add(Calendar.MONTH, 1);
        }

        return list;
    }

    /**
     * 指定的时间内取YYYYMMDD的列表
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getYYYYMMDDList(Date start, Date end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            if (calendar.after(endCalendar)) {
                break;
            }
            list.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return list;
    }

    public static String getStringByDate(long time, String format) {
        return getStringByDate(new Date(time), format);
    }


    /**
     * 取今天结束时间(23:59:59)
     *
     * @return
     */
    public static long getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime() + 999L;
    }


    public static String getStringByDate(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String strDate = sdf.format(date);
            return strDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 日期增加月数，可以为负数
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2012-01-03");
        Date d2 = sdf.parse("2014-01-03");
        System.out.println(getYYYYMMDDList(d1, d2));

    }

    /**
     * 取日期范围内的日期(yyyy-MM-dd)数组
     *
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static List<String> getDateListByRange(String startDate, String endDate, String format) {
        List<String> dates = new ArrayList<>();
        if (format == null) format = "yyyy-MM-dd";

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(TimeUtil.getDate(startDate, format));
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(TimeUtil.getDate(endDate, format));

        while (!calendarStart.after(calendarEnd)) {
            dates.add(TimeUtil.getStringByDate(calendarStart.getTime(), "yyyy-MM-dd"));
            calendarStart.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dates;

    }

    /**
     * 取日期范围内的日期(yyyy-MM)数组
     *
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static List<String> getMonthListByRange(String startDate, String endDate, String format) {
        List<String> months = new ArrayList<>();
        if (format == null) format = "yyyy-MM-dd";

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(TimeUtil.getDate(startDate, format));
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(TimeUtil.getDate(endDate, format));

        while (!calendarStart.after(calendarEnd)) {
            months.add(TimeUtil.getStringByDate(calendarStart.getTime(), "yyyy-MM"));
            calendarStart.add(Calendar.MONTH, 1);
        }

        return months;
    }

    /**
     * 取当前日期所在月份第一天
     *
     * @param date
     * @return
     */
    public static long getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 取前日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static long getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime() + 999L;
    }


    /**
     * 判定是不是今天
     *
     * @param time 时间毫秒串(<strong>注意:精确到毫秒</strong>)
     * @return
     * @author xuefei
     */
    public static boolean isToday(final Long time) {
        if (null == time) {
            return false;
        }
        Calendar target = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        target.setTimeInMillis(time);
        if (target.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && target.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                && target.get(Calendar.DAY_OF_MONTH) == today
                .get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 时间范围校验
     *
     * @param time    毫秒时间
     * @param expired 过期时间
     * @return
     */
    public static boolean validTime(long time, long expired) {
        long diff = System.currentTimeMillis() - time;
        if (diff > expired || diff < -expired) {
            return false;
        }
        return true;
    }


    /**
     * 获取格式化的时间字符串
     * @param date   时间
     * @param format   格式
     * @return
     */
    public static String getDateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
