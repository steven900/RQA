package com.jdog.frameworks.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具
 * @author Jdog.Asher
 * @Date 2014-1-17
 */
public class CalendarUtil {

	/**
	 * 计算任意两个时间点之间相差的天数，不计算 时分秒 默认按照 "yyyy-MM-dd HH:mm:ss"
	 * @param date1 string
	 * @param date2 string
	 * @return 如果截获到 字符串 转 DATE的异常，则返回-1
	 */
	public static int countDaysBetweenDates(String date1, String date2) {
		return countDaysBetweenDates(date1, date2, "yyyy-MM-dd HH:mm:dd");
	}
	
	/**
	 * 计算任意两个时间点之间相差的天数，不计算 时分秒
	 * @param date1 string
	 * @param date2 string
	 * @param formatPattern JAVA格式化时间日期字符串 如"yyyy-MM-dd HH:mm:ss"
	 * @return 如果截获到 字符串 转 DATE的异常，则返回-1
	 */
	public static int countDaysBetweenDates(String date1, String date2, String formatPattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			return countDaysBetweenDates(d1, d2);
		} catch(ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 计算任意两个时间点之间相差的天数，不计算 时分秒
	 * @param date1 java.util.Date
	 * @param date2 java.util.Date
	 * @return
	 */
	public static int countDaysBetweenDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int totalDays = 0;
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 == year2) {
			int day1 = cal1.get(Calendar.DAY_OF_YEAR);
			int day2 = cal2.get(Calendar.DAY_OF_YEAR);
			totalDays = Math.abs(day1 - day2);
		} else {
			int tempYear = year1;
			if(year1 > year2) {
				year1 = year2;
				year2 = tempYear;
			}
			// 加入中间年份的天数
			for(int i=year1 + 1;i<year2;i++) {
				totalDays += countDaysOfYear(i);
			}
			// 加入year1的剩余天数
			totalDays += countDaysOfYear(year1) - cal1.get(Calendar.DAY_OF_YEAR);
			totalDays += cal2.get(Calendar.DAY_OF_YEAR);
		}
		return totalDays;
	}
	
	/**
	 * 计算指定年份一共有多少周 默认为当前年
	 * @return
	 */
	public static int countWeeksOfYear() {
		return countWeeksOfYear(Calendar.getInstance().get(Calendar.YEAR));
	}
	
	/**
	 * 计算指定年份一共有多少周
	 * @param year 年份(公历,如:2014)
	 * @return
	 */
	public static int countWeeksOfYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		return cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 计算指定年一共有多少天 默认为当前年
	 */
	public static int countDaysOfYear() {
		return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 计算指定年一共有多少天
	 * @param year 年份(公历,如:2014)
	 * @return
	 */
	public static int countDaysOfYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	}
}
