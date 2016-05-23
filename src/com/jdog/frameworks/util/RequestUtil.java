package com.jdog.frameworks.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;

import com.jdog.frameworks.encode.Escape;

public class RequestUtil {
	

	public static String toString(HttpServletRequest request, String name) {
		return request.getParameter(name);
	}

	public static String toString(HttpServletRequest request, String name,
			String defaultValue) {
		String value = toString(request, name);
		if (value == null || value.equals(""))
			return defaultValue;
		return value;
	}

	public static String toChinese(HttpServletRequest request, String name) {
		return StringUtil.ISO88591ToGBK(request.getParameter(name));
	}

	public static String toChinese(HttpServletRequest request, String name,
			String defaultValue) {
		String value = toChinese(request, name);
		if (value == null)
			return defaultValue;
		return value;
	}

	public static int toInt(HttpServletRequest request, String name,
			int defaultValue) {
		return NumberUtils.toInt(toString(request, name), defaultValue);
	}

	public static int toInt(HttpServletRequest request, String name) {
		return NumberUtils.toInt(toString(request, name), 0);
	}

	public static float toFloat(HttpServletRequest request, String name,
			float defaultValue) {
		String value = toString(request, name);
		if (value == null || value.equals(""))
			return defaultValue;
		return Float.valueOf(value);
	}

	public static float toFloat(HttpServletRequest request, String name) {
		return toFloat(request, name, 0);
	}

	public static double toDouble(HttpServletRequest request, String name,
			double defaultValue) {
		String value = toString(request, name);
		if (value == null || value.equals(""))
			return defaultValue;
		return Double.valueOf(value);
	}

	public static double toDouble(HttpServletRequest request, String name) {
		return toDouble(request, name, 0);
	}

	public static double toLong(HttpServletRequest request, String name,
			double defaultValue) {
		String value = toString(request, name);
		if (value == null || value.equals(""))
			return defaultValue;
		return Long.parseLong(value);
	}

	public static double toLong(HttpServletRequest request, String name) {
		return toLong(request, name, 0);
	}

	public static Date toDate(HttpServletRequest request, String name,
			Date defaultValue) {
		String value = toString(request, name);
		if (value == null || value.equals(""))
			return defaultValue;
		return DateUtil.parseShort(value);
	}

	public static Date toDate(HttpServletRequest request, String name) {
		return toDate(request, name, null);
	}

	public static String decodeUrl(HttpServletRequest request) {
		return decodeUrl(request, "url");
	}

	public static String decodeUrl(HttpServletRequest request, String name) {
		String url = toString(request, name);
		return StringUtil.hex2str(url);
	}

	public static String encodeUrl(HttpServletRequest request) {
		return encodeUrl(request, "url");
	}

	public static String encodeUrl(HttpServletRequest request, String name) {
		String url = toString(request, name);
		String queryString = request.getQueryString();
		if (queryString != null) {
			try {
				queryString = new String(queryString.getBytes("ISO-8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String requestUrl = request.getRequestURI()
				+ (request.getQueryString() == null ? "" : "?" + queryString);
		if(requestUrl.indexOf("&forwardUrl=") > -1) {
			String tail = requestUrl.substring(requestUrl.indexOf("forwardUrl"));
			if(tail.indexOf("&") > -1) {
				tail = tail.substring(tail.indexOf("&"));
			} else {
				tail = "";
			}
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("&forwardUrl=")) + tail;
		}
		if (url == null)
			url = StringUtil.str2hex(requestUrl).toLowerCase();
		return url;
	}

	public static String encodeFullUrl(HttpServletRequest request) {
		return encodeFullUrl(request, "url");
	}

	public static String encodeFullUrl(HttpServletRequest request, String name) {
		String url = toString(request, name);
		String queryString = request.getQueryString();
		if (queryString != null) {
			try {
				queryString = new String(queryString.getBytes("ISO-8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String requestUrl = "http://" + request.getServerName()
				+ request.getRequestURI()
				+ (request.getQueryString() == null ? "" : "?" + queryString);
		if (url == null)
			url = StringUtil.str2hex(requestUrl).toLowerCase();
		return url;
	}

	public static int sessionToInt(HttpServletRequest request, String name) {
		return sessionToInt(request, name, 0);
	}

	public static int sessionToInt(HttpServletRequest request, String name,
			int defaultValue) {
		Object obj = request.getSession().getAttribute(name);
		return NumberUtils.toInt(obj + "", defaultValue);
		// int val = defaultValue;
		// if (obj != null)
		// val = NumberUtils.toInt(obj.toString());
		// return val;
	}
	
	public static Long sessionToLong(HttpServletRequest request, String name) {
		return sessionToLong(request, name, 0L);
	}
	
	public static Long sessionToLong(HttpServletRequest request, String name,
			Long defaultValue) {
		Object obj = request.getSession().getAttribute(name);
		return NumberUtils.toLong(obj + "", defaultValue);
	}

	public static String sessionToString(HttpServletRequest request, String name) {
		Object obj = request.getSession().getAttribute(name);
		String val = null;
		if (obj != null)
			val = obj.toString();
		return val;
	}

	public static float sessionToFloat(HttpServletRequest request, String name) {
		return sessionToFloat(request, name, 0);
	}

	public static float sessionToFloat(HttpServletRequest request, String name,
			int defaultValue) {
		Object obj = request.getSession().getAttribute(name);
		return NumberUtils.toFloat(obj + "", defaultValue);
	}

	public static int reqToInt(HttpServletRequest request, String name) {
		return reqToInt(request, name, 0);
	}

	public static int reqToInt(HttpServletRequest request, String name,
			int defaultValue) {
		Object obj = request.getAttribute(name);
		return NumberUtils.toInt(obj + "", defaultValue);
	}

	public static float reqToFloat(HttpServletRequest request, String name) {
		return reqToFloat(request, name, 0);
	}

	public static float reqToFloat(HttpServletRequest request, String name,
			int defaultValue) {
		Object obj = request.getAttribute(name);
		return NumberUtils.toFloat(obj + "", defaultValue);
	}

	public static String reqToString(HttpServletRequest request, String name) {
		Object obj = request.getAttribute(name);
		String val = null;
		if (obj != null)
			val = obj.toString();
		return val;
	}

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		} else {
			String[] ips = ip.split(",");
			ip = ips[0];
			if (ip.equals("unknown")) {
				ip = ips[1].trim();
			}
		}
		return ip;
	}

	public static String ajaxToString(HttpServletRequest request, String name,
			String defaultValue) {
		String value = ajaxToString(request, name);
		if (value == null || value.trim().equals("")) {
			value = defaultValue;
		}
		return value;
	}

	public static String ajaxToString(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		return Escape.unescape(Escape.unescape(value));
	}

	/**
	 * 获取参数列表,返回Map(忽略大小写) , 若无重复项,则只取一项,否则返回String[]
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getParameters(HttpServletRequest request) {
		Map<String, Object> m = new HashMap<String, Object>();
		Map<String, String[]> s = request.getParameterMap();
		for (Entry<String, String[]> en : s.entrySet()) {
			if (en.getValue().length == 1) {
				m.put(en.getKey(), en.getValue()[0]);
			} else {
				m.put(en.getKey(), en.getValue());
			}
		}

		return m;
	}

	/**
	 * 当前WEB应用根目录
	 * 
	 * @return
	 * @throws IllegalAccessException
	 */
	public static String getWebRoot() {
		try {
			String path = getWebClassesPath();
			if (path.indexOf("WEB-INF") > 0) {
				path = path.substring(1, path.indexOf("WEB-INF/classes"));
			} else {
				throw new IllegalAccessException("路径获取错误");
			}
			return path;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 当前WEB应用类目录
	 * 
	 * @return
	 */
	public static String getWebClassesPath() {
		String path = new RequestUtil().getClass().getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		return path;
	}

	/**
	 * 判断是否为网络爬虫
	 */
	public static boolean isRobotRequest(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		if (userAgent == null) {
			return false;
		}

		userAgent = userAgent.toLowerCase();

		if (userAgent.contains("spider")) {
			return true;
		} else if (userAgent.contains("bot")) {
			return true;
		} else if (userAgent.contains("nutch")) {
			return true;
		} else if (userAgent.contains("yahoo")) {
			return true;
		} else if (userAgent.contains("gougou")) {
			return true;
		} else if (userAgent.contains("scooter")) {
			return true;
		} else if (userAgent.contains("lilina")) {
			return true;
		}
		return false;
	}

}
