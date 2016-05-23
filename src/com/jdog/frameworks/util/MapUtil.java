package com.jdog.frameworks.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapUtil {

	public static String toString(Map<String, Object> map, String name) {
		return toString(map, name, null);
	}

	public static String toString(Map<String, Object> map, String name,
			String defaultValue) {
		Object obj = map.get(name);
		if (obj == null)
			return defaultValue;
		return obj.toString();
	}

	public static int toInt(Map<String, Object> map, String name) {
		return toInt(map, name, 0);
	}

	public static int toInt(Map<String, Object> map, String name,
			int defaultValue) {
		Object obj = map.get(name);
		if (obj == null)
			return defaultValue;
		return Integer.parseInt(obj.toString());
	}

	public static long toLong(Map<String, Object> map, String name) {
		return toLong(map, name, 0);
	}

	public static long toLong(Map<String, Object> map, String name,
			int defaultValue) {
		Object obj = map.get(name);
		if (obj == null)
			return defaultValue;
		return Long.parseLong(obj.toString());
	}

	public static float toFloat(Map<String, Object> map, String name) {
		return toFloat(map, name, 0);
	}

	public static float toFloat(Map<String, Object> map, String name,
			float defaultValue) {
		Object obj = map.get(name);
		if (obj == null)
			return defaultValue;
		return Float.parseFloat(obj.toString());
	}

	public static double toDouble(Map<String, Object> map, String name,
			double defaultValue) {
		Object obj = map.get(name);
		if (obj == null)
			return defaultValue;
		return Double.valueOf(obj.toString());
	}

	public static double toDouble(Map<String, Object> map, String name) {
		return toDouble(map, name, 0);
	}

	public static Date toDate(Map<String, Object> map, String name,
			Date defaultValue) {
		Object obj = map.get(name);
		if (obj == null)
			return defaultValue;
		return (Date) obj;
	}

	public static Date toDate(Map<String, Object> map, String name) {
		return toDate(map, name, null);
	}

	/**
	 * Map排序
	 * 
	 * @param unsort_map
	 * @return
	 */
	public static SortedMap<String, Object> mapSortByKey(
			Map<String, Object> unsort_map) {
		TreeMap<String, Object> result = new TreeMap<String, Object>();

		Object[] unsort_key = unsort_map.keySet().toArray();
		Arrays.sort(unsort_key);

		for (int i = 0; i < unsort_key.length; i++) {
			result.put(unsort_key[i].toString(), unsort_map.get(unsort_key[i]));
		}
		return result.tailMap(result.firstKey());
	}

	/**
	 * String转JSON 注:字串不能包含双引号、单引号等 例如：floorId:1,count:2,cache:0 ==>
	 * {"floorId":"1","count":"2","cache":"0"}
	 * 
	 * @param str
	 * @return
	 */
	public static String String2Json(String str) {
		String s[] = str.split(",");
		String json = "{";
		for (int i = 0; i < s.length; i++) {
			String s1[] = s[i].split(":");

			if (i > 0)
				json += ",";

			json += "\"" + s1[0] + "\":\"" + s1[1] + "\"";
		}
		json += "}";
		return json;
	}

	/**
	 * bean转map
	 * @param <T>
	 */
	public static <T> Map<String, Object> bean2Map(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Field.setAccessible(fields, true);
			for(int i=0; i<fields.length; i++) {
				if(fields[i].get(obj) instanceof Date) {
					map.put(fields[i].getName(), DateUtil.format((Date)fields[i].get(obj), "yyyy-MM-dd HH:mm:ss"));
				} else {
					map.put(fields[i].getName(), fields[i].get(obj));
				}
			}
		} catch(Exception e){
			e.printStackTrace(); 
		}
		return map;
	}
	
	/**
	 * map转bean
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> T map2Bean(Map<String, Object> map, Class<T> cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Method[] methods = cls.getDeclaredMethods();
		for(Method m : methods) {
			String methodName = m.getName();
			Class<T>[] types = (Class<T>[]) m.getParameterTypes();
			if(types.length != 1) {
				continue;
			}
			if(methodName.indexOf("set") < 0) {
				continue;
			}
			String type = types[0].getSimpleName();
			
			Object field = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
			if(map.containsKey(field)) {
				Object value = map.get(field);
				try {
					if(type.equals("String")) {
						m.invoke(obj, new Object[] {value});
					} else if(type.equals("int") || type.equals("Integer")) {
						m.invoke(obj, new Object[] {new Integer(value + "")});
					} else if(type.equals("double") || type.equals("Double")) {
						m.invoke(obj, new Object[] {new Double(value + "")});
					} else if(type.equals("float") || type.equals("Float")) {
						m.invoke(obj, new Object[] {new Float(value + "")});
					} else if(type.equals("long") || type.equals("Long")) {
						m.invoke(obj, new Object[] {new Long(value + "")});
					} else if(type.equals("boolean") || type.equals("Boolean")) {
						m.invoke(obj, new Object[] {new Boolean(value + "")});
					} else if(type.equals("BigDecimal")) {
						m.invoke(obj, new Object[] {new BigDecimal(value + "")});
					} else if(type.equals("Date")) {
						Date date = null;
						if(value.getClass().getName().equals("java.util.Date")) {
							date = (Date) value;
						} else {
							String format = ((String) value).indexOf(":") > 0 ? "yyyy-MM-dd hh:mm:ss" : "yyyy-MM-dd";
							SimpleDateFormat sf = new SimpleDateFormat();
							sf.applyPattern(format);
							date = sf.parse((String) value);
						}
						if(date != null) {
							m.invoke(obj, new Object[] {date});
						}
					} else if(type.equals("byte[]")) {
						m.invoke(obj, new Object[] {new String(value + "").getBytes()});
					} else if(type.equals("int") || type.equals("Integer")) {
						m.invoke(obj, new Object[] {new Integer("fieldName")});
					} else if(type.equals("int") || type.equals("Integer")) {
						m.invoke(obj, new Object[] {new Integer("fieldName")});
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (T) obj;
	}
	
}
