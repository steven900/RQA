package com.jdog.frameworks.entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jdog.frameworks.annotation.constraint.MaxLength;
import com.jdog.frameworks.annotation.constraint.MaxValue;
import com.jdog.frameworks.annotation.constraint.MinLength;
import com.jdog.frameworks.annotation.constraint.MinValue;
import com.jdog.frameworks.annotation.constraint.NotNull;
import com.jdog.frameworks.annotation.constraint.Regular;
import com.jdog.frameworks.annotation.entity.Compare;
import com.jdog.frameworks.annotation.field.DBDate;
import com.jdog.frameworks.annotation.field.Ref;
import com.jdog.frameworks.log.Log;

/**
 * 针对实体的数据验证器
 * 只能验证 继承自 com.jdog.frameworks.entity.BaseEntity 的所有实体
 * @author Jdog.Asher
 * @Date 2014-3-4
 */
public class EntityValidator {
	
	/**
	 * 数据验证
	 * @param entity 目标检查对象
	 * @param logSwitch 日志开关
	 * @return
	 */
	public static boolean validate(BaseEntity entity) {
		try {
			Field[] declaredFields = entity.getClass().getDeclaredFields();
			for(Field declaredField : declaredFields) {
				// 获取方法
				String methodName = "get" + declaredField.getName().substring(0, 1).toUpperCase() + declaredField.getName().substring(1);
				Method method = null;
				try {
					 method = entity.getClass().getDeclaredMethod(methodName);
				} catch(NoSuchMethodException e) {
					// 未匹配到方法，跳过
					continue;
				}
				// 字段类型
				Class<?> fieldType = declaredField.getType();
				// 是否为原始类型
				boolean isPrimitive = fieldType.isPrimitive();

				if(declaredField.getAnnotation(Ref.class) != null) {
					continue;
				}
				
				// 获取全部注解
				Annotation[] fieldAnnotations = declaredField.getAnnotations();
				
				for(Annotation annotation : fieldAnnotations) {
					Object value = method.invoke(entity);
					
					// 处理非空标记 原始类型将得不到通过，请不要在业务逻辑核心entity中使用原始类型 int,char,boolean,float,double,byte
					if(annotation instanceof NotNull || annotation instanceof Compare) {
						if(declaredField.getAnnotation(DBDate.class) != null) {
							continue;
						}
						if(!isPrimitive) {
							if(value == null) {
								Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "对象不能为空");
								return false;
							}
						}
					}
					// 检查最大长度 如果是数字类型的，计算位数
					if(annotation instanceof MaxLength) {
						if(value != null) {
							int maxLength = ((MaxLength) annotation).length();
							if(value instanceof String) {
								if(((String) value).length() > maxLength) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "字符串超过最大长度");
									return false;
								}
							}
							if(value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long) {
								String v = String.valueOf(value);
								if(v.length() > maxLength) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "数字位数超过最大位数");
									return false;
								}
							}
							if(value instanceof Float || value instanceof Double) {
								String v = String.valueOf(value);
								if(v.length() - 1 > maxLength) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "数字位数超过最大位数");
									return false;
								}
							}
						}
					}
					// 检查最小长度 如果是数字类型的，计算位数
					if(annotation instanceof MinLength) {
						if(value != null) {
							int minLength = ((MinLength) annotation).length();
							if(value instanceof String) {
								if(((String) value).length() < minLength) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "字符串小于最小长度");
									return false;
								}
							}
							if(value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long) {
								String v = String.valueOf(value);
								if(v.length() < minLength) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "数字位数小于最小位数");
									return false;
								}
							}
							if(value instanceof Float || value instanceof Double) {
								String v = String.valueOf(value);
								if(v.length() - 1 < minLength) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "数字位数小于最小位数");
									return false;
								}
							}
						}
					}
					// 检查最大值
					if(annotation instanceof MaxValue) {
						if(value != null) {
							if(value instanceof Byte || value instanceof Short || value instanceof Integer 
									|| value instanceof Long || value instanceof Float || value instanceof Double) {
								double v = Double.parseDouble(value.toString());
								double maxValue = ((MaxValue) annotation).value();
								if(v > maxValue) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "超过最大值");
									return false;
								}
							}
						}
					}
					
					// 检查最小值
					if(annotation instanceof MinValue) {
						if(value != null) {
							if(value instanceof Byte || value instanceof Short || value instanceof Integer 
									|| value instanceof Long || value instanceof Float || value instanceof Double) {
								double v = Double.parseDouble(value.toString());
								double minValue = ((MinValue) annotation).value();
								if(v < minValue) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "小于最小值");
									return false;
								}
							}
						}
					}
					
					// 检查正则表达式
					if(annotation instanceof Regular) {
						if(value != null) {
							if(value instanceof String) {
								String pattern = ((Regular) annotation).pattern();
								Pattern p = Pattern.compile(pattern);
								Matcher m = p.matcher((String) value);
								if(!m.matches()) {
									Log.debug(entity.getClass().getName() + "." + declaredField.getName() + "::" + "不匹配正则表达式【" + pattern + "】");
									return false;
								}
							}
						}
					}
				}
			}
			
			// 所有字段的所有注解全部通过检查
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
