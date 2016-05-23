package com.jdog.frameworks.db;

/**
 * 分页SQL
 * 
 * @author yaoyu
 * 
 */

public class PagerQuery {

	/***
	 * @param sql 查询语句
	 * @param orderby 排序方式 如 addtime desc , addtime 必须在查询语句中指明
	 * @param page 查询页 从1开始
	 * @param rownum 每页行数
	 */
	public static String pagerQueryString(String sql, String orderby, int page, int rowSize) {
		return pagerQueryString(sql + " order by  " + orderby, page, rowSize);
	}

	/***
	 * @param sql 查询语句
	 * @param page 查询页 从1开始
	 * @param rowSize 每页行数
	 */
	public static String pagerQueryString(String sql, int page, int rowSize) {
		int start = (page - 1) * rowSize;
		String _sql = String.format("%s limit %s, %s", sql, start, rowSize);
		return _sql;
	}

	public static String queryNString(String sql, int rowsize) {
		return pagerQueryString(sql, 1, rowsize);
	}

}
