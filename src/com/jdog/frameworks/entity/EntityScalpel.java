package com.jdog.frameworks.entity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jdog.frameworks.util.DateUtil;
import com.jdog.frameworks.util.StringUtil;

/**
 * 实体解剖刀
 * 用于各种对象的直接获取属性,复制属性等
 * @author AsherYuan
 * 2015年5月13日
 */
public class EntityScalpel {
	
	/**
	 * 将对象转化为Map
	 * @param obj 实体对象
	 * @return HashMap<String, Object>
	 */
	public static Map<String, Object> obj2Map(Object obj) {
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
	 * 获取实体对象的某个属性
	 * @param obj 对象
	 * @param name 属性名称
	 * @return
	 */
	public static Object getProperties(Object obj, String name) {
		if(StringUtil.isBlank(name) || name.length() < 2) {
			return null;
		}
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for(int i=0; i<fields.length; i++) {
				if(fields[i].getName().equals(name)) {
					return fields[i].get(obj);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
