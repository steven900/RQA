package com.jdog.frameworks.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.jdog.frameworks.annotation.field.DBDate;
import com.jdog.frameworks.annotation.field.DefaultValue;
import com.jdog.frameworks.annotation.field.Identifier;
import com.jdog.frameworks.annotation.field.Ref;
import com.jdog.frameworks.annotation.field.UpdateField;

public class BaseDao<T> {

	private static int MAX = 9999;

	/**
	 * 修改通过
	 * 
	 * @param pass
	 * @param id
	 */
	public void indexShow(int indexShow, int id) {
		String tableName = _getEntityClass().getSimpleName();
		String sql = "update " + tableName + " set indexShow=? where id=? ";
		DB.update(sql, indexShow, id);
	}

	/**
	 * 修改通过
	 * 
	 * @param pass
	 * @param id
	 */
	public void pass(int pass, int id) {
		String tableName = _getEntityClass().getSimpleName();
		String sql = "update " + tableName + " set pass=? where id=? ";
		DB.update(sql, pass, id);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		String tableName = _getEntityClass().getSimpleName();
		String sql = "update " + tableName + " set available=0 where id=? ";
		DB.update(sql, id);
	}

	/**
	 * 排序
	 */
	public void dorder(int dorder, int id) {
		String tableName = _getEntityClass().getSimpleName();
		String sql = "update " + tableName + " set dorder=? where id=? ";
		DB.update(sql, dorder, id);
	}

	/**
	 * 获取当前最大排序值
	 * 
	 * @return
	 */
	public int getMaxDorder() {
		String tableName = _getEntityClass().getSimpleName();
		String sql = "select max(dorder) from " + tableName;
		return DB.queryForInt(sql) + 1;
	}

	/**
	 * 查询返回分页列表(Bean)
	 * 
	 * @param sql
	 * @param sqlCount
	 * @param page
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public ListPage<T> listPage(String sql, String sqlCount, int page,
			int pageSize, Object... params) {
		if (page <= 0)
			page = 1;
		sql = PagerQuery.pagerQueryString(sql, page, pageSize);
		int rowCount = DB.queryForInt(sqlCount, params);
		int pageCount = (rowCount + pageSize - 1) / pageSize;

		List<T> list = null;

		Class<T> entityClass = _getEntityClass();
		list = DB.queryForBeanList(sql, entityClass, params);

		ListPage<T> ypage = new ListPage<T>(list, page, pageSize, pageCount,
				rowCount);
		return ypage;
	}

	/**
	 * 查询返回列表(Bean)
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> queryForBeanList(String sql, Object... params) {
		Class<T> entityClass = _getEntityClass();
		return DB.queryForBeanList(sql, entityClass, params);
	}

	/**
	 * 查询返回列表N(Bean)
	 * 
	 * @param sql
	 * @param count
	 * @param params
	 * @return
	 */
	public List<T> queryNForBeanList(String sql, int count, Object... params) {
		if (count == 0 || count > 9999) {
			count = MAX;
		}
		sql = PagerQuery.pagerQueryString(sql, 1, count);
		Class<T> entityClass = _getEntityClass();
		return DB.queryForBeanList(sql, entityClass, params);
	}

	/**
	 * 查询返回一个Bean
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public T queryForBean(String sql, Object... params) {
		List<T> list = this.queryNForBeanList(sql, 1, params);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	

	public long insert(T t) {
		Class<T> clazz = _getEntityClass();
		String tableName = clazz.getSimpleName();

		String fieldStr = "";
		String paramStr = "";
		ArrayList<Object> params = new ArrayList<Object>();

		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field f : fields) {
				String methodName = "get"
						+ f.getName().substring(0, 1).toUpperCase()
						+ f.getName().substring(1);
				Method method = null;
				try {
					method = clazz.getDeclaredMethod(methodName);
					if (f.getAnnotation(Ref.class) != null) {
						continue;
					} else if (f.getAnnotation(Identifier.class) != null) {
						fieldStr += f.getName() + ", ";
						paramStr += "null, ";
					} else if (f.getAnnotation(DBDate.class) != null) {
						fieldStr += f.getName() + ", ";
						paramStr += "now(), ";
					} else if (f.getAnnotation(DefaultValue.class) != null) {
						DefaultValue dv = f.getAnnotation(DefaultValue.class);
						String val = dv.val();
						fieldStr += f.getName() + ", ";
						paramStr += val + ", ";
					} else {
						fieldStr += f.getName() + ", ";
						paramStr += "?, ";
						params.add(method.invoke(t));
					}
				} catch (NoSuchMethodException e) {
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if (fieldStr.endsWith(", ")) {
			fieldStr = fieldStr.substring(0, fieldStr.length() - 2);
		}
		if (paramStr.endsWith(", ")) {
			paramStr = paramStr.substring(0, paramStr.length() - 2);
		}
		String sql = "insert into " + tableName + "(" + fieldStr + ") values ("
				+ paramStr + ")";
		return DB.insert(sql, params.toArray());
	}

	public int update(T t) {
		Class<T> clazz = _getEntityClass();
		String tableName = clazz.getSimpleName();

		String fieldStr = "";
		Integer id = null;
		ArrayList<Object> params = new ArrayList<Object>();

		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field f : fields) {
				String methodName = "get"
						+ f.getName().substring(0, 1).toUpperCase()
						+ f.getName().substring(1);
				Method method = null;
				try {
					method = clazz.getDeclaredMethod(methodName);
					if (f.getAnnotation(Ref.class) != null) {
						continue;
					} else if (f.getAnnotation(UpdateField.class) != null) {
						if (f.getAnnotation(DBDate.class) != null) {
							fieldStr += f.getName() + "=now(), ";
						} else {
							fieldStr += f.getName() + "=?, ";
							params.add(method.invoke(t));
						}
					} else if (f.getAnnotation(Identifier.class) != null) {
						id = (Integer) method.invoke(t);
					}
				} catch (NoSuchMethodException e) {
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if (fieldStr.endsWith(", ")) {
			fieldStr = fieldStr.substring(0, fieldStr.length() - 2);
		}
		String sql = "update " + tableName + " set " + fieldStr
				+ " where id=? ";
		params.add(id);
		return DB.update(sql, params.toArray());
	}

	/**
	 * 获取实体类
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<T> _getEntityClass() {
		Class<T> entityClass = null;
		try {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityClass;
	}

}