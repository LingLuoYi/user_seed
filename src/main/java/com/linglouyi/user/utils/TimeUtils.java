package com.linglouyi.user.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linglouyi
 */
public class TimeUtils {

    /**
     * 将传入时间格式为0时
     */
    public static Date getZeroTime(Date time) {
        long current = time.getTime();
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return new Date(zero);
    }

    /**
     * 将出入时间格式为24时
     */
    public static Date getEndTime(Date time) {
        long current = time.getTime();
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;
        return new Date(twelve);
    }

    /**
     * 获取传入时间的星期一
     */
    public static Date getMonday(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        //获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取星期天的日期
     *
     * @return
     */
    public static Date getSunday(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        //获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.add(Calendar.DATE, 6);
        return cal.getTime();
    }

    /**
     * 获取每个月第一天
     *
     * @param time
     * @return
     */
    public static Date getOneDay(Date time) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(time);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 获取每个月最后一天
     *
     * @param time
     * @return
     */
    public static Date getEndDay(Date time) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(time);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    public static Date getYearFirst(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        return calendar.getTime();
    }

    public static Date getYearLast(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取自定义天数
     *
     * @return
     */
    public static Date getCustomizeDay(Date currentTime, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static List<Date> findDaysStr(Date begintTime, Date endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> daysStrList = new ArrayList<Date>();
        daysStrList.add(begintTime);
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(begintTime);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endTime);
        while (endTime.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            daysStrList.add(calBegin.getTime());
        }
        return daysStrList;
    }

    /**
     * 判断当前时间是否大于预设时间，大于true
     * @param date 时间
     * @param second 预加毫秒
     * @return boolean
     */
    public static boolean timeOut(Date date,long second){
        long oldTime = date.getTime() + second;
        long time = System.currentTimeMillis();
        return oldTime > time;
    }

}
