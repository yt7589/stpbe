package com.zhuanjingkj.zjcbe.utility.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenxuewei
 * @description:时间日期转换操作类
 * @since 2019-05-16
 */
public class DateTimeUtil {

	public static String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

	public static String FORMAT_DATE = "yyyy-MM-dd";

	public static String FORMAT_DATE_INT = "yyyyMMdd";

	public static String FORMAT_HOUR = "HH:mm";

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static Date now() {
		return getTime();
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static Date getTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 获取当前时间 yyyy-MM-dd
	 */
	public static Date getTime(String date) {
		try {
			return new SimpleDateFormat(FORMAT_TIME).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 获取当前时间 yyyy-MM-dd
	 */
	public static Date getDate(String date) {
		try {
			return new SimpleDateFormat(FORMAT_DATE).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 获取当前时间
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_TIME);
		return df.format(getTime());
	}

	/**
	 * 获取当前时间(HH:MM)
	 */
	public static String getHourString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_HOUR);
		return df.format(date);
	}

	/**
	 * 时间转字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_TIME);
		String enddate = format.format(date);
		return enddate;
	}

	/**
	 * 获取日期字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE);
		String enddate = format.format(date);
		return enddate;
	}

	/**
	 * 获取日期字符串
	 *
	 * @param date
	 * @return
	 */
	public static Integer getDateInt(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_INT);
		String enddate = format.format(date);
		return Integer.parseInt(enddate);
	}

	/**
	 * 获取日期年份
	 *
	 * @param date 日期
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月
	 *
	 * @param date Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日期
	 *
	 * @param date Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 *
	 * @param date 日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分
	 *
	 * @param date 日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 *
	 * @param date Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫
	 *
	 * @param date 日期
	 * @return 返回毫
	 */
	public static Date addYears(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, num);
		Date currdate = calendar.getTime();
		return currdate;
	}

	/**
	 * 增加分钟
	 *
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addMonths(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		Date currdate = calendar.getTime();
		return currdate;
	}

	/**
	 * 添加天数
	 *
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addDays(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		Date currdate = calendar.getTime();
		return currdate;
	}

	/**
	 * 添加小时
	 *
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addHours(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, num);
		Date currdate = calendar.getTime();
		return currdate;
	}

	/**
	 * 增加分钟
	 *
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addMinutes(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, num);
		Date currdate = calendar.getTime();
		return currdate;
	}

	/**
	 * 增加秒钟
	 *
	 * @param num
	 * @param date
	 * @return
	 */
	public static Date addSeconds(int num, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, num);
		Date currdate = calendar.getTime();
		return currdate;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间间隔天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDays(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间间隔天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Double getTotalDays(Date date1, Date date2) {
		return (double) (date2.getTime() - date1.getTime()) / (1000 * 3600 * 24 * 1.0);
	}

	/**
	 * 获取两个时间差（分钟）
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMinutes(Date date1, Date date2) {
		int minutes = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60));
		return minutes;
	}
}
