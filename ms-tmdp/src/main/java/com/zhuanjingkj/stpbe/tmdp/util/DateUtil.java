package com.zhuanjingkj.stpbe.tmdp.util;

import org.apache.tomcat.jni.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {

	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static DateTimeFormatter dtfYmd = DateTimeFormatter.ofPattern("yyyyMMdd");

	public static DateTimeFormatter DTF_NYR = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //年月日

	public static DateTimeFormatter DTF_MD = DateTimeFormatter.ofPattern("MM-dd");

	public static DateTimeFormatter DTF_YM = DateTimeFormatter.ofPattern("yyyy-MM");

	public static DateTimeFormatter dtfYm1 = DateTimeFormatter.ofPattern("yyyyMM");

	public static DateTimeFormatter DTF_HM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	/**
	 * 获取当前时间字符串 2021-01-28 10:03:42
	 */
	public static String getLocalDateTime(){
		return dtf.format(LocalDateTime.now()); 
	}
	
	/**
	 * 
	 * @Description: 判断时间有效期 
	 * @param @param vtime 有效时间
	 * @return boolean    返回类型 
	 */
	public static boolean isBefore(String date){
		return LocalDateTime.now().isBefore(LocalDateTime.parse(date, dtf));
	}
	
	public static boolean isTimeBefore(String time){
		return LocalTime.now().isBefore(LocalTime.parse(time));
	}
	
	public static boolean isTimeAfter(String time){
		return LocalTime.now().isAfter(LocalTime.parse(time));
	}

	/**
	 * days 2021-01-28 > 2021-01-27
	 * @param num = -1
	 * @return
	 */
	public static String countDays(Integer num){
		LocalDate now = LocalDate.now();
		return now.plusDays(num).toString();
	}

	/**
	 * 返回当前日期dd：2021-01-28 09:55:41 > 28
	 * @return
	 */
	public static Integer getDay(){
		return LocalDate.now().getDayOfMonth();
	}

	/**
	 * 日期转换：2020-01-01 > 20200101
	 * @param localDate
	 * @return
	 */
	public static String getDayOfMonth(LocalDate localDate){
		return localDate.format(dtfYmd);
	}

	/**
	 *年月：2020-01
	 * @return
	 */
	public static String getMonthOfYear(){
		return LocalDate.now().format(DTF_YM);
	}

	/**
	 * days 2021-01-28 10:16:10 > + num 2021-01-29 10:16:31
	 * @param date
	 * @param num = 1
	 * @return
	 */
	public static String plusDays(String date, Integer num){
		LocalDateTime ldt = LocalDateTime.parse(date, dtf);
		return ldt.plusDays(num).format(dtf).toString();
	}

	/**
	 * 2021-01-28 10:16 > 2021-01-29 10:16
	 * @param date
	 * @param num = 1
	 * @return
	 */
	public static String plus7Days(String date, Integer num, DateTimeFormatter dtf){
		System.out.println("date:" + date);
		LocalDateTime ldt = LocalDateTime.parse(date, dtf);
		return ldt.plusDays(num).format(dtf).toString();
	}

	/**
	 * 2021-01-28 > 2021-01-29
	 * @param date
	 * @param num = 1
	 * @return
	 */
	public static String plusDays(String date, Integer num, DateTimeFormatter dtf){
		System.out.println("date:" + date);
		LocalDate ldt = LocalDate.parse(date, dtf);
		return ldt.plusDays(num).format(dtf).toString();
	}

	/**
	 * days 2021-01-28 >  + num 2021-01-29
	 * @param date
	 * @param num = 1
	 * @return
	 */
	public static String plusDaysForDate(String date, Integer num){
		LocalDate ldt = LocalDate.parse(date, DTF_NYR);
		return ldt.plusDays(num).format(DTF_NYR).toString();
	}

	/**
	 * months 2021-01-28 10:11:18 > + num 2021-02-28 10:11:41
	 * @param date
	 * @param num =1
	 * @return
	 */
	public static String plusMonth(String date, Integer num){
		LocalDateTime localDateTime = LocalDateTime.parse(date, dtf);
		return localDateTime.plusMonths(num).format(dtf).toString();
	}

	/**
	 * hours 2021-01-28 10:15:20 > + num 2021-01-28 11:15:20
	 * @param date
	 * @param num = 1
	 * @return
	 */
	public static String plusHours(String date, Integer num){
		return LocalDateTime.parse(date, dtf).plusHours(num).format(dtf);
	}

	/**
	 * months 2021-01-28 10:24:36 > +num  2021-02-28 10:24:36
	 * @param num = 1
	 * @return
	 */
	public static String countMonth(Integer num){
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.plusMonths(num).format(dtf).toString();
	}

	/**
	 * months 2021-01-28 10:24:36 > +num  2021-02-28 10:24:36
	 * @param num = 1
	 * @return
	 */
	public static String countMonthYm1(Integer num){
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.plusMonths(num).format(dtfYm1).toString();
	}

	/**
	 * days 20210128 > + num 20210129
	 * @param num = 1
	 * @return
	 */
	public static String plusDays(Integer num) {
		LocalDate localDate = LocalDate.now();
		return localDate.plusDays(num).format(dtfYmd).toString();
	}

	/**
	 * 时间戳转日期 172214104 > 1970-01-03 07:50:14
	 * @param timeStamp
	 * @return
	 */
	public static String timeStamp2Date (String timeStamp) {
		Long l = Long.parseLong(timeStamp);
		LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochMilli(l), TimeZone.getDefault().toZoneId());
		return dtf.format(now);
	}

	/**
	 * 连续30天的map
	 * @return
	 */
	public static Map<String, Integer> dayFor30Map(Integer num, DateTimeFormatter dtf) {
		String endTime = LocalDate.now().format(dtf);
		SortedMap<String, Integer> resMap = new TreeMap<>();
		for(int i =0; i < num; i++) {
			resMap.put((plusDaysForDate(endTime, -i)), 0);
		}
		return resMap;
	}

	/**
	 * 12个月份 2020-01 ...
	 * @return
	 */
	public static Map<String, Integer> monthFor12Map() {
		Integer year = LocalDate.now().getYear();
		SortedMap<String, Integer> resMap = new TreeMap<>();
		for (int i =1; i < 13; i++) {
			if(i < 10) {
				resMap.put(year + "-0" + i, 0);
			} else {
				resMap.put(year + "-" + i, 0);
			}
		}
		return resMap;
	}

	/**
	 * 12个月份 2020-01 ...
	 * @return
	 */
	public static Map<String, Integer> timeFor24Map() {
		SortedMap<String, Integer> resMap = new TreeMap<>();
		for (int i =1; i <= 24; i++) {
			if(i < 10) {
				resMap.put("0" + i, 0);
			} else {
				resMap.put("" + i, 0);
			}
		}
		return resMap;
	}
	/**
	 * flag = false 往前数
	 * @param num
	 * @param flag
	 * @return
	 */
	public static SortedMap<String, Integer> monthFor3Map(Integer num, boolean flag) {
		SortedMap<String, Integer> resMap = new TreeMap<>();
		Integer nm = 0;
		for(int i = 0; i < num; i++) {
			nm = flag == true ? i : -i;
			resMap.put(LocalDate.now().plusMonths(nm).format(DateTimeFormatter.ofPattern("yyyy-MM")), 0);
		}
		return resMap;
	}
	/**
	 * 日期转换：2021-01-01 > 01-01
	 * @param date
	 * @return
	 */
	public static String timeForMdStr(String date) {
		LocalDate ld = LocalDate.parse(date);
		return ld.format(DTF_MD);
	}

	public static Map<String, Integer> yearForMap(Integer num) {
		SortedMap<String, Integer> sortedMap = new TreeMap<>();
		for (int i =0; i < num; i++) {
			sortedMap.put("" + LocalDate.now().plusYears(-i).getYear(), 0);
		}
		return sortedMap;
	}
	public static void main(String[] args) {
		System.out.println("getLocalDateTime:" + getLocalDateTime());
		System.out.println("isBefore:" + isBefore("2020-08-03 14:48:26"));
		System.out.println("getMonthOfYear:" + getMonthOfYear());
		System.out.println("isTimeBefore:" + isTimeBefore("17:00"));
		System.out.println("isTimeAfter:" + isTimeAfter("05:00"));
		System.out.println("plusMonth:" + plusMonth("2020-09-27 10:19:22", 3));
		System.out.println("getDay:" + getDay());
		System.out.println("getMonthValue:" + LocalDate.now().getMonthValue());
		System.out.println("getDayOfMonth:" + getDayOfMonth(LocalDate.now().plusDays(3)));
		System.out.println("plusHours:" + plusHours("2020-09-27 10:19:22", 72));
		System.out.println("countMonth:" + countMonth(-1));
		System.out.println("plusDays:" + plusDays("2020-08-27 10:19:22", -1));
		System.out.println("countDays:" + countDays(-1));
		System.out.println("plusDaysForDate:" + plusDaysForDate("2021-01-04", -1));
		System.out.println("getHour:" + LocalDateTime.now().getHour());
		int hour = LocalDateTime.now().getHour();
		String date = "" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + (hour < 10 ? ("0" + hour) : hour) ;
		System.out.println("getHour:" + date);
		System.out.println("plusDays:" + plusDays(-1));
		System.out.println("plusDays:" +plusDays(0));
		System.out.println("timeStamp2Date" + timeStamp2Date("172214104"));
		System.out.println("plusDaysForDate" + plusDaysForDate("2021-01-25", -29));
		Map<String, Integer> map = dayFor30Map(7, DateUtil.DTF_NYR);
		for (String key : map.keySet()) {
			System.out.println("key:" + key +"value:" + map.get(key));
		}
		System.out.println(timeForMdStr("2021-01-27"));

		System.out.println("plusDays:" + plus7Days("2021-01-28 10:19", -7, DTF_NYR));

		System.out.println("monthFor3Map"  + monthFor3Map(3, false));
	}

}
