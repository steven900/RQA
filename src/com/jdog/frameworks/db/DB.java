package com.jdog.frameworks.db;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.mortbay.log.Log;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

public class DB {
	
	// 日志开关
	public static final boolean LOGSWITCH = false;
	
	static {
		BeanUtilsBean beanUtilsBean = new CaseInsensitiveBeanUtilsBean();
		BeanUtilsBean.setInstance(beanUtilsBean);
	}
	
	private static void DBLog(String sql, Object... params) {
		if(LOGSWITCH) {
			Log.info("执行sql:" + sql);
			String str = "";
			if(params != null && params.length > 0) {
				for(Object o : params) {
					str += o + ";";
				}
			}
			Log.info("参数:" + str);
		}
	}
	
	public static float queryForFloat(String sql, Object... params) {
		Float i = queryForObject(sql, Float.class, params);
		return i == null ? 0f : i;
	}

	public static float queryForFloat(String sql, Map<String, Object> params) {
		Float i = queryForObject(sql, Float.class, params);
		return i == null ? 0 : i;
	}
	
	public static double queryForDouble(String sql, Object... params) {
		Double i = queryForObject(sql, Double.class, params);
		return i == null ? 0f : i;
	}

	public static double queryForDouble(String sql, Map<String, Object> params) {
		Double i = queryForObject(sql, Double.class, params);
		return i == null ? 0 : i;
	}
	
	public static int queryForInt(String sql, Object... params) {
		Integer i = queryForObject(sql, Integer.class, params);
		return i == null ? 0 : i;
	}

	public static int queryForInt(String sql, Map<String, Object> params) {
		Integer i = queryForObject(sql, Integer.class, params);
		return i == null ? 0 : i;
	}

	public static long queryForLong(String sql, Object... params) {
		Long i = queryForObject(sql, Long.class, params);
		return i == null ? 0L : i;
	}

	public static long queryForLong(String sql, Map<String, Object> params) {
		Long i = queryForObject(sql, Long.class, params);
		return i == null ? 0L : i;
	}

	public static String queryForString(String sql, Object... params) {
		return queryForObject(sql, String.class, params);
	}

	public static String queryForString(String sql, Map<String, Object> params) {
		return queryForObject(sql, String.class, params);
	}

	public static Map<String, Object> queryForMap(String sql, Object... params) {
		List<Map<String, Object>> ret = queryNForList(sql, 1, params);
		if (ret.size() > 0) { return ret.get(0);}
		return null;
	}

	public static <T extends Serializable> T queryForBean(String sql, Class<T> cls, Object... params) {
		List<T> ret = queryNForBeanList(sql, 1, cls, params);
		if (ret.size() > 0){ return ret.get(0);}
		return null;
	}
	
	public static <T extends Serializable> T queryForBean(String sql, T target, Object... params) {
		try {
			BeanUtils.populate(target, queryForMap(sql, params));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return target;
	}
	
	public static <T> T queryForObject(String sql, Class<T> cls, Object... params) {
		List<T> rets = queryForObjectList(sql, cls, params);
		return rets.size() == 1 ? rets.get(0) : null;
	}
	
	public static <T> List<T> queryForObjectList(String sql, Class<T> cls, Object... params) {
		DBLog(sql, params);
		return (List<T>) SpringBeanFactory.getJdbcTemplate().queryForList(sql, params, cls);
	}

	public static List<Map<String, Object>> queryForList(String sql, Object... params) {
		DBLog(sql, params);
		return caseInsensitiveWrapper(SpringBeanFactory.getJdbcTemplate().queryForList(sql, params));
	}
	
	public static List<Map<String, Object>> queryNForList(String sql, int n, Object... params) {
		DBLog(sql, params);
		return caseInsensitiveWrapper(SpringBeanFactory.getJdbcTemplate().queryForList(PagerQuery.queryNString(sql, n), params));
	}

	public static <T> List<T> queryForBeanList(String sql, Class<T> cls, Object... params) {
		return listMapToBean(cls, queryForList(sql, params));
	}
	
	public static <T> List<T> queryNForBeanList(String sql, int n, Class<T> cls, Object... params) {
		return listMapToBean(cls, queryNForList(sql, n, params));
	}
	
	public static List<Map<String, Object>> queryPageForList(String sql, int pageno, int rowSize, Object... params) {
		DBLog(sql, params);
		return caseInsensitiveWrapper(SpringBeanFactory.getJdbcTemplate().queryForList(pageQueryForObjects(sql, pageno, rowSize), params));
	}

	public static <T> List<T> queryPageForBeanList(String sql, int pageno, int rowSize, Class<T> cls, Object... params) {
		return listMapToBean(cls, queryPageForList(sql, pageno, rowSize, params));
	}

	public static int update(String sql, Object... params) {
		DBLog(sql, params);
		return SpringBeanFactory.getJdbcTemplate().update(sql, params);
	}
	
	/**
	 * 批处理
	 * @param sql
	 * @param batchArgs
	 * @return 返回每一次执行SQL,操作的记录条数
	 */
	public static int[] batchUpdate(String sql, List<Object[]> batchArgs) {
		if(LOGSWITCH) {
			Log.info("执行批处理:" + sql);
			if(batchArgs.size() > 0) {
				for(int i=0;i<batchArgs.size();i++) {
					Object[] os = batchArgs.get(i);
					String params = "";
					for(int j=0;j<os.length;j++) {
						params += String.valueOf(os[j]) + " ";
					}
					Log.info("第" + i + "组参数:" + params);
				}
			}
		}
		return SpringBeanFactory.getJdbcTemplate().batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 新增操作返回主键ID
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Long insert(final String sql, final Object... params) {
		DBLog(sql, params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SpringBeanFactory.getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				for(int i=0; i<params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				return ps;
			}
		}, keyHolder);
		Long key = keyHolder.getKey().longValue();
		return key;
	}
	
	
	/**
	 * 查询返回分页列表
	 * @param sql
	 * @param sqlCount
	 * @param page
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public static ListPage<Map<String, Object>> listPage(String sql,
			String sqlCount, int page, int pageSize, Object... params) {
		if (page <= 0)
			page = 1;

		int rowCount = DB.queryForInt(sqlCount, params);
		int pageCount = (rowCount + pageSize - 1) / pageSize;

		sql = PagerQuery.pagerQueryString(sql, page, pageSize);
		List<Map<String, Object>> list = (List<Map<String, Object>>) DB
				.queryForList(sql, params);

		ListPage<Map<String, Object>> ypage = new ListPage<Map<String, Object>>(
				list, page, pageSize, pageCount, rowCount);

		return ypage;
	}
	
	/**
	 * 将结果map映射到指定的bean上
	 * @param cls
	 * @param list
	 * @return
	 */
	private static <T> List<T> listMapToBean(Class<T> cls, List<Map<String, Object>> list) {
		List<T> target = new ArrayList<T>();
		for (Map<String, Object> t : list) {
			try {
				T tar = cls.newInstance();
				ConvertUtils.register(new IntegerConverter(null), Integer.class);
				BeanUtils.populate(tar, t);
				target.add(tar);
			} catch (InstantiationException e) {
				throw new RuntimeException();
			} catch (IllegalAccessException e) {
				throw new RuntimeException();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return target;
	}
	
	/***
	 * @param sql 查询语句
	 * @param page 查询页 从1开始
	 * @param rowSize 每页行数
	 */
	public static String pageQueryForObjects(String sql, int page, int rowSize) {
		int start = (page - 1) * rowSize;
		return String.format("%s limit %s, %s", sql, start, rowSize);
	}
	
	
	/**
	 * 将结果Map中的key全部转化为小写
	 */
	private static List<Map<String, Object>> caseInsensitiveWrapper(List<Map<String, Object>> listMap) {
		List<Map<String, Object>> listMapTarget = new ArrayList<Map<String, Object>>(listMap.size());
		for (Map<String, Object> m : listMap) {
			listMapTarget.add(caseInsensitiveWrapper(m));
		}
		return listMapTarget;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> caseInsensitiveWrapper(Map<String, Object> map) {
		return new CaseInsensitiveMap(map);
	}
	
}