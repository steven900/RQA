package com.jdog.frameworks.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.helper.StringUtil;

public class DateUtil {

	private static String DF_LONG = "yyyy-MM-dd HH:mm:ss";
	private static String DF_SHORT = "yyyy-MM-dd";
	
	public static String now() {
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DF_LONG);
		return sdf.format(myDate);
	}

	public static String nowShort() {
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DF_SHORT);
		return sdf.format(myDate);
	}

	public static Date parse(String str) {
		return parse(str, DF_LONG);
	}

	public static Date parseShort(String str) {
		return parse(str, DF_SHORT);
	}

	public static String toString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(DF_LONG);
		return sdf.format(date);
	}

	public static String toStringShort(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DF_SHORT);
		return sdf.format(date);
	}
	
	public static String format(Date date, String date_format) {
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
		return sdf.format(date);
	}

	public static String format(String str, String date_format) {
		try {
			if (date_format.equals(""))
				date_format = DF_LONG;
			if (date_format.equals("date"))
				date_format = DF_SHORT;
			Date date = parse(str);
			SimpleDateFormat sdf = new SimpleDateFormat(date_format);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parse(String str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(str);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	public static long getLong() {
		Date myDate = new Date();
		return myDate.getTime();
	}

	public static long getLong(String str) {
		Date date = parse(str);
		return date.getTime();
	}

	public static long getLongShort(String str) {
		Date date = parse(str, DF_SHORT);
		return date.getTime();
	}
	
	
	
	/** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static  String getSpecifiedDayBefore(String start) {//可以用new Date().toLocalString()传递参数  
    	
    	if(StringUtil.isBlank(start)) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			start = DateUtil.format(cal.getTime(), "yyyy-MM-dd");
//			start = "2014-11-01";
		}
    	return start;
	
    }
    
    public static String getNow(String start){
    	if(StringUtil.isBlank(start)) {
			Calendar cal = Calendar.getInstance();
			start = DateUtil.format(cal.getTime(), "yyyy-MM-dd");
//			start = "2014-11-01";
		}
    	return start;
    }
    
    public static  Date getSpecifiedDayBeforeTwo() {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = new Date();  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
        return c.getTime();  
	
    }
    
    /**
     * 获取一天的开始时间
     * @param date
     * @return
     */
    public static String getStartOfDate(String date){
    	
    	if( date == null || date.equals("")){
    		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            String str = ft.format(new Date());
            return str;
    	}
        return date+" 00:00:00";
    }
    
    public static String getStringdate(Date date){
    	if(date==null)
    		return "";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = ft.format(date);
        return str;
    }

    /**
     * 获取一天的结束时间
     * @param date
     * @return
     */
    public static String getEndOfDate(String date){
    	if( date == null || date.equals("")){
    		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String str = ft.format(new Date());
            return str;
    	}
        return date+" 23:59:59";
    }
    
    public static String getAnHourBefore(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR ,  -1 );
		return DateUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String getFiveMinsBefore(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE ,  -5 );
		return DateUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }
    public static String getOneHourBefore(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR ,  -1 );
		return DateUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 获取前一天
     * @return
     */
    public static Date getOneDayBefore(){
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE ,  -1 );
    	return cal.getTime();
    }
    
    public static Date getTenDaysBefore(Date date){
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE ,  -10 );
    	return cal.getTime();
    }
    
    public static String getOneMonthBefore(){
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH ,  -1 );
		return DateUtil.format(cal.getTime(), "yyyy-MM-dd ");
    }
    public static String getOneWeekBefore(){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -7);
		return DateUtil.format(cal.getTime(), "yyyy-MM-dd");
    }
    public static String getHalfYearBefore(){
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH ,  -6 );
		return DateUtil.format(cal.getTime(), "yyyy-MM-dd");
    }

	public static String getOneYearBefore() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		return DateUtil.format(cal.getTime(), "yyyy-MM-dd");
	}
	
	public static int DatecompareWithToday(){
		
		return 1;
	}
	
    /** 
     * 功  能：判断是否是同一天<br /> 
     * 备  注：<br /> 
        @param d1 
        @param d2 
        @return  
     */  
    public static boolean isTheSameDay(Date d1,Date d2){  
    	long MS_OF_ONE_DAY=3600000*24;
        if(d1!=null && d2!=null) {  
            final long time = d1.getTime();  
            final long time2 = d2.getTime();  
            long l = time/MS_OF_ONE_DAY; // 
            long l2 = time2/MS_OF_ONE_DAY;  
            return  l==l2;  
        }  
        return false;  
    }  
    public static Date getCurrentDateStartTime(){
    	
    	SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date now = new Date();  
        try {  
            now = shortSdf.parse(shortSdf.format(now));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return now;
    }
    
    public static String getWeekDay(Calendar c){
    	   if(c == null){
    	    return "星期一";
    	   }
    	  
    	   if(Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)){
    	    return "星期一";
    	   }
    	   if(Calendar.TUESDAY == c.get(Calendar.DAY_OF_WEEK)){
    	    return "星期二";
    	   }
    	   if(Calendar.WEDNESDAY == c.get(Calendar.DAY_OF_WEEK)){
    	    return "星期三";
    	   }
    	   if(Calendar.THURSDAY == c.get(Calendar.DAY_OF_WEEK)){
    	    return "星期四";
    	   }
    	   if(Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)){
    	    return "星期五";
    	   }
    	   if(Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)){
    	    return "星期六";
    	   }
    	   if(Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)){
    	    return "星期日";
    	   }
    	   return "星期一";
    	}
}