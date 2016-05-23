package com.jdog.frameworks.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jdog.frameworks.annotation.entity.Compare;
import com.jdog.frameworks.annotation.entity.Entity;
import com.jdog.frameworks.exception.entity.BaseEntityParamTypeException;

@Entity
public class BaseEntity extends Object {
	
	// 升序排序器
	private static Comparator<BaseEntity> ascComparator;
	// 降序排序器
	private static Comparator<BaseEntity> descComparator;
	
	public static Comparator<BaseEntity> ascComparator() {
		return ascComparator;
	}

	public static Comparator<BaseEntity> descComparator() {
		return descComparator;
	}
	
	/**
	 * 重写的toString()方法
	 * 
	 */
	public String toString() {
		// 得到类中的成员变量
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Method method = this.getClass().getMethod(methodName, new Class[]{});
				Object value = method.invoke(this, new Object[]{});
				sb.append(fieldName + ":");
				sb.append(value + " ");
			} catch(SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 默认的构造函数
	 * 初始化比较器，用于排序
	 */
	public BaseEntity() {
		ascComparator = new Comparator<BaseEntity>() {
			public int compare(BaseEntity obj1, BaseEntity obj2) {
				try {
					// 判断是否同一类型
					String obj1ClassName = obj1.getClass().getName();
					String obj2ClassName = obj2.getClass().getName();

					if(!obj1ClassName.equals(obj2ClassName)) {
						throw new BaseEntityParamTypeException();
					}
					return obj1.compareTo(obj2);
				} catch (BaseEntityParamTypeException e) {
					e.printStackTrace();
				}
				return 0;
			}
		};
		
		descComparator = new Comparator<BaseEntity>() {
			public int compare(BaseEntity obj1, BaseEntity obj2) {
				try {
					// 判断是否同一类型
					String obj1ClassName = obj1.getClass().getName();
					String obj2ClassName = obj2.getClass().getName();

					if(!obj1ClassName.equals(obj2ClassName)) {
						throw new BaseEntityParamTypeException();
					}
					return obj2.compareTo(obj1);
				} catch (BaseEntityParamTypeException e) {
					e.printStackTrace();
				}
				return 0;
			}
		};
	}
	
	/**
	 * 重构 比较大小 方法
	 * 如果 两个对象相等 返回 0
	 * 如果 this > obj 返回正数
	 * 如果 this < obj 返回负数
	 * 
	 * 如果是map类型的，比较的是map中key的个数
	 * 如果是list类型的，比较的是list的长度
	 */
	public int compareTo(Object obj) {
		String thisClassName = this.getClass().getName();
		String objClassName = obj.getClass().getName();
		// 类判断是否同一类的实体
		if(thisClassName.equals(objClassName)) {
			// 获取所有带有 @Compare 注解的字段
			List<FieldPriority> preparedToCompare = getPreparedFields();
			
			// 开始比较
			for(FieldPriority fp : preparedToCompare) {
				Field f = fp.getField();
				String methodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				try {
					// 获取对应 get方法 和对应的值
					Method method = this.getClass().getDeclaredMethod(methodName);
					Class<?> classType = method.getReturnType();
					if(classType == int.class || classType == Integer.class) {
						int thisValue = (Integer) method.invoke(this);
						int objValue = (Integer) method.invoke(obj);
						if(thisValue > objValue) {
							return 1;
						} else if(thisValue < objValue) {
							return -1;
						}
					}
					if(classType == float.class || classType == Float.class) {
						float thisValue = (Float) method.invoke(this);
						float objValue = (Float) method.invoke(obj);
						if(thisValue > objValue) {
							return 1;
						} else if(thisValue < objValue) {
							return -1;
						}
					}
					if(classType == double.class || classType == Double.class) {
						double thisValue = (Double) method.invoke(this);
						double objValue = (Double) method.invoke(obj);
						if(thisValue > objValue) {
							return 1;
						} else if(thisValue < objValue) {
							return -1;
						}
					}
					if(classType == boolean.class || classType == Boolean.class) {
						boolean thisValue = (Boolean) method.invoke(this);
						boolean objValue = (Boolean) method.invoke(obj);
						if(thisValue && !objValue) {
							return 1;
						} else if(!thisValue && objValue) {
							return -1;
						}
					}
					if(classType == String.class) {
						String thisValue = (String) method.invoke(this);
						String objValue = (String) method.invoke(obj);
						if(thisValue.compareTo(objValue) > 0) {
							return 1;
						} else if(thisValue.compareTo(objValue) < 0) {
							return -1;
						}
					}
					if(classType == Map.class) {
						@SuppressWarnings("unchecked")
						Map<String, Object> thisValue = (Map<String, Object>) method.invoke(this);
						@SuppressWarnings("unchecked")
						Map<String, Object> objValue = (Map<String, Object>) method.invoke(obj);
						if(thisValue.size() > objValue.size()) {
							return 1;
						} else if(thisValue.size() < objValue.size()) {
							return -1;
						}
					}
					if(classType == List.class) {
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> thisValue = (List<Map<String, Object>>) method.invoke(this);
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> objValue = (List<Map<String, Object>>) method.invoke(obj);
						if(thisValue.size() > objValue.size()) {
							return 1;
						} else if(thisValue.size() < objValue.size()) {
							return -1;
						}
					}
					if(classType == Date.class) {
						Date thisValue = (Date) method.invoke(this);
						Date objValue = (Date) method.invoke(obj);
						if(thisValue.compareTo(objValue) > 0) {
							return 1;
						} else if(thisValue.compareTo(objValue) < 0) {
							return -1;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				throw new BaseEntityParamTypeException();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	private List<FieldPriority> getPreparedFields() {
		// 获取所有带有 @Compare 注解的字段
		List<FieldPriority> preparedToCompare = new ArrayList<FieldPriority>();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f : fields) {
			if(f.getAnnotation(Compare.class) != null) {
				int priority = f.getAnnotation(Compare.class).priority();
				FieldPriority fp = new FieldPriority();
				fp.setField(f);
				fp.setPriority(priority);
				preparedToCompare.add(fp);
			}
		}
		
		// 根据priority进行排序
		// 注册比较器
		Comparator<FieldPriority> comparator = new Comparator<FieldPriority>() {
			public int compare(FieldPriority fp1, FieldPriority fp2) {
				return fp1.getPriority() - fp2.getPriority();
			}
		};
		Collections.sort(preparedToCompare, comparator);
		return preparedToCompare;
	}

	private class FieldPriority {
		private Field field;
		private int priority;
		public Field getField() {
			return field;
		}
		public void setField(Field field) {
			this.field = field;
		}
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
	}
}
