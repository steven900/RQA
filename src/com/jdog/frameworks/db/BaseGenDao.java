package com.jdog.frameworks.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.helper.StringUtil;

import com.jdog.frameworks.annotation.field.DBField;
import com.jdog.frameworks.annotation.field.DBPrikey;

public class BaseGenDao<T> extends BaseDao<T> {

	public T findByFields(T t) {
		Class<T> clazz = _getEntityClass();
		String tableName = clazz.getSimpleName();
		List<Object> params = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		//SqlAnnotationGen.sqllist(t, params, where);
		String wh = "";
		if (params.size() > 0) {
			wh = " where " + where.toString();
		}
		String orderby = "";
		String sql = " select * from " + tableName + wh + orderby;
		return this.queryForBean(sql, params.toArray());
	}

	public int countByFields(T t) {
		Class<T> clazz = _getEntityClass();
		String tableName = clazz.getSimpleName();
		List<Object> params = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		//SqlAnnotationGen.sqllist(t, params, where);
		String wh = "";
		if (params.size() > 0) {
			wh = " where " + where.toString();
		}
		String sql = " select  count(0) from " + tableName + wh;
		return DB.queryForInt(sql, params.toArray());

	}

	public int updateByPriKey(T t) {
		Class<T> clazz = _getEntityClass();
		String tableName = clazz.getSimpleName();
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuffer updates = new StringBuffer();
			//SqlAnnotationGen.sqlupdate(t, updates, params);
			
			String wh = "";
			Field[] fiels = clazz.getDeclaredFields();
			for (Field f : fiels) {
				f.setAccessible(true);
				boolean fz = f.isAnnotationPresent(DBPrikey.class);
				if (fz) {
					Object fieldobj;
					fieldobj = f.get(t);
					String fieldname = f.getName();
					if (fieldobj != null) {
						params.add(fieldobj);
						wh = " "+fieldname+"=? ";
					}
				}
			}
			if (params.size() < 1) {
				return 0;
			}
			if(!StringUtil.isBlank(wh)){
				wh = " where "+wh;
			}
			String sql = " update "+tableName+" set " + updates.toString()
					+ wh;
			return DB.update(sql, params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long save(T t) {
		try {
			Class<T> clazz = _getEntityClass();
			String tableName = clazz.getSimpleName();
			ArrayList<Object> params = new ArrayList<Object>();
			Field[] fiels = clazz.getDeclaredFields();
			String keys = "";
			String values = "";
			for (Field f : fiels) {
				f.setAccessible(true);

				boolean fz = f.isAnnotationPresent(DBField.class);

				if (fz) {
					Object fieldobj;
					fieldobj = f.get(t);
					String fieldname = f.getName();
					if (fieldobj != null) {

						if (params.size() > 0) {
							keys = keys + "," + fieldname;
							values = values + "," + "?";
						} else {
							keys = fieldname;
							values = "?";
						}
						params.add(fieldobj);
					}
				}
			}
			String sql = "insert into " + tableName + "(" + keys + ") values ("
					+ values + ")";
			return DB.insert(sql, params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
