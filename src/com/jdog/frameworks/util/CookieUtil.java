package com.jdog.frameworks.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdog.frameworks.encode.DESPlus;

public class CookieUtil {

	private static int MAX_AGE = 180 * 24 * 60 * 60; // 180天
	private static String KEY = "2005~2100.china.jdog.com";
	private static String domain; // 根域

	public static String getCookie(HttpServletRequest request, String name) {
		String value = null;
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ((cookies[i].getName()).equals(name)) {
					value = cookies[i].getValue();
					break;
				}
			}
		}

		// 解密
		if (value != null) {
			try {
				DESPlus des = new DESPlus(KEY);
				value = des.decrypt(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	public static void setCookie(HttpServletResponse response, String name,
			String value) {
		// 加密
		try {
			DESPlus des = new DESPlus(KEY);
			value = des.encrypt(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(MAX_AGE); // 设置Cookie的存活时间
		cookie.setDomain(domain);// 根域为nuanjx.com
		cookie.setPath("/");
		response.addCookie(cookie); // 写入客户端硬盘
	}

	public static void setCookie(HttpServletResponse response, String name,
			String value, int age) {
		// 加密
		try {
			DESPlus des = new DESPlus(KEY);
			value = des.encrypt(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(age); // 设置Cookie的存活时间
		cookie.setDomain(domain);// 根域为nuanjx.com
		cookie.setPath("/");
		response.addCookie(cookie); // 写入客户端硬盘
	}

	public static String getCookieNoDes(HttpServletRequest request, String name) {
		String value = null;
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ((cookies[i].getName()).equals(name)) {
					value = cookies[i].getValue();
					break;
				}
			}
		}
		return value;
	}

	/**
	 * cookie不加密，考虑到js读取比对cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookieNoDes(HttpServletResponse response,
			String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(MAX_AGE); // 设置Cookie的存活时间
		cookie.setDomain(domain);// 根域为nuanjx.com
		cookie.setPath("/");
		response.addCookie(cookie); // 写入客户端硬盘
	}

	public static void removeCookie(HttpServletResponse response,
			HttpServletRequest request, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0); // 设置Cookie的存活时间
		cookie.setDomain(domain);// 根域为nuanjx.com
		cookie.setPath("/");
		response.addCookie(cookie); // 写入客户端硬盘
	}

	/**
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	public static String getUuid(HttpServletRequest request,
			HttpServletResponse response) {
		String uuid = CookieUtil.getCookieNoDes(request, "uuid");
		if (uuid == null) {
			uuid = request.getSession().getId();
			CookieUtil.setCookieNoDes(response, "uuid", uuid);
		}
		return uuid;
	}

}
