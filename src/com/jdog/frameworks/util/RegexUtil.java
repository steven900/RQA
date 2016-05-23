package com.jdog.frameworks.util;

import java.util.regex.Pattern;

/**
 * 正则表达式库
 * @author Jdog.Asher
 * @Date 2014-1-17
 */
public class RegexUtil {

	public static void main(String[] args) {
		boolean flag = test(MOBILE, "124q345243");
		boolean flag1 = test(MOBILE, "18668384816");
		System.out.println(flag + "::" + flag1);
	}
	
	/*****************************************  数字 begin  ***********************************************/
	public static String DECIMAL = "^\\d+\\.?\\d+$"; /** 浮点数数 **/
	public static String INTEGER = "^\\d+$"; /** 整数 **/
	public static String NUMBER = "^\\d*\\.?\\d*$"; /** 数字 **/
	
	/*****************************************  数字 end    ***********************************************/
	
	/*****************************************  字符串 end    *********************************************/
	public static String CHINESE = "^[\u4e00-\u9fa5]*$"; /** 汉字 **/
	public static String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"; /** Email **/
	public static String WWWURL = "^[hH][tT][tT][pP][sS]?://([\\w-]*\\.)+[\\w-]*(/([\\w-\\+\\?%&=]*))?$"; /** 网址 **/
	public static String MOBILE = "^[1][3,4,5,8][0-9]{9}$"; /** 手机号码  **/
	/*****************************************  字符串 end    *********************************************/


	public static boolean test(String regex, String target) {
		return target == null ? false : target.trim().equals("") ? false : Pattern.compile(regex).matcher(target).find();
	}
}
