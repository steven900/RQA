package com.jdog.frameworks.db;

import java.util.List;

public class SqlUtil {

	/**
	 * SQL条件拼接
	 * 
	 * @param where
	 * @param params
	 * @param sql
	 * @param value
	 */
	static public void append(StringBuffer where, List<Object> params,
			String sql, Object value) {
		if (!where.toString().equals("")) {
			where.append(" and ");
		}
		where.append(sql);
		params.add(value);
	}

	static public void append(StringBuffer where, String sql) {
		if (!where.toString().equals("")) {
			where.append(" and ");
		}
		where.append(sql);
	}

	static public void append(StringBuffer where, List<Object> params,
			String sql, Object[] values) {
		if (!where.toString().equals("")) {
			where.append(" and ");
		}
		where.append(sql);
		for (Object value : values) {
			params.add(value);
		}
	}

	/**
	 * 如果没有可选条件和默认条件时使用
	 * 
	 * @param where
	 */
	static public void append(StringBuffer where) {
		append(where, "1=1");
	}
}
