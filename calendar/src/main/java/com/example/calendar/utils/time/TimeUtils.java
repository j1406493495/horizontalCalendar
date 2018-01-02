package com.example.calendar.utils.time;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wong on 2018/1/2.
 * TimeUtils
 */
public final class TimeUtils {
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(final long millis) {
        return millis2String(millis, DEFAULT_FORMAT);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为 format</p>
     *
     * @param millis 毫秒时间戳
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String millis2String(final long millis, final DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Millis(final String time) {
        return string2Millis(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(final String time, final DateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为 Date 类型
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date 类型
     */
    public static Date string2Date(final String time) {
        return string2Date(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为 Date 类型
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date 类型
     */
    public static Date string2Date(final String time, final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 Date 类型转为时间字符串
     * <p>格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date Date 类型时间
     * @return 时间字符串
     */
    public static String date2String(final Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }

    /**
     * 将 Date 类型转为时间字符串
     * <p>格式为 format</p>
     *
     * @param date   Date 类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(final Date date, final DateFormat format) {
        return format.format(date);
    }

    /**
     * 将 Date 类型转为时间戳
     *
     * @param date Date 类型时间
     * @return 毫秒时间戳
     */
    public static long date2Millis(final Date date) {
        return date.getTime();
    }

    /**
     * 将时间戳转为 Date 类型
     *
     * @param millis 毫秒时间戳
     * @return Date 类型时间
     */
    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }

    /**
     * 获取当前毫秒时间戳
     *
     * @return 毫秒时间戳
     */
    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串
     * <p>格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getNowString() {
        return millis2String(System.currentTimeMillis(), DEFAULT_FORMAT);
    }

    /**
     * 获取当前时间字符串
     * <p>格式为 format</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getNowString(final DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }

    /**
     * 获取当前 Date
     *
     * @return Date 类型时间
     */
    public static Date getNowDate() {
        return new Date();
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 判断是否今天
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isToday(final String time) {
        return isToday(string2Millis(time, DEFAULT_FORMAT));
    }

    /**
     * 判断是否今天
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isToday(final String time, final DateFormat format) {
        return isToday(string2Millis(time, format));
    }

    /**
     * 判断是否今天
     *
     * @param date Date 类型时间
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isToday(final Date date) {
        return isToday(date.getTime());
    }

    /**
     * 判断是否今天
     *
     * @param millis 毫秒时间戳
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    /**
     * 获取中式星期
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 中式星期
     */
    public static String getChineseWeek(final String time) {
        return getChineseWeek(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * 获取中式星期
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 中式星期
     */
    public static String getChineseWeek(final String time, final DateFormat format) {
        return getChineseWeek(string2Date(time, format));
    }

    /**
     * 获取中式星期
     *
     * @param date Date 类型时间
     * @return 中式星期
     */
    public static String getChineseWeek(final Date date) {
        return new SimpleDateFormat("E", Locale.CHINA).format(date);
    }

    /**
     * 获取中式星期
     *
     * @param millis 毫秒时间戳
     * @return 中式星期
     */
    public static String getChineseWeek(final long millis) {
        return getChineseWeek(new Date(millis));
    }

    /**
     * 获取美式星期
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 美式星期
     */
    public static String getUSWeek(final String time) {
        return getUSWeek(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * 获取美式星期
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 美式星期
     */
    public static String getUSWeek(final String time, final DateFormat format) {
        return getUSWeek(string2Date(time, format));
    }

    /**
     * 获取美式星期
     *
     * @param date Date 类型时间
     * @return 美式星期
     */
    public static String getUSWeek(final Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }

    /**
     * 获取美式星期
     *
     * @param millis 毫秒时间戳
     * @return 美式星期
     */
    public static String getUSWeek(final long millis) {
        return getUSWeek(new Date(millis));
    }

    /**
     * 获取星期索引
     * <p>注意：周日的 Index 才是 1，周六为 7</p>
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final String time) {
        return getWeekIndex(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * 获取星期索引
     * <p>注意：周日的 Index 才是 1，周六为 7</p>
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final String time, final DateFormat format) {
        return getWeekIndex(string2Date(time, format));
    }

    /**
     * 获取星期索引
     * <p>注意：周日的 Index 才是 1，周六为 7</p>
     *
     * @param date Date 类型时间
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取星期索引
     * <p>注意：周日的 Index 才是 1，周六为 7</p>
     *
     * @param millis 毫秒时间戳
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final long millis) {
        return getWeekIndex(millis2Date(millis));
    }
}
