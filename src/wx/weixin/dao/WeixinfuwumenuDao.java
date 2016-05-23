package wx.weixin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;
import com.jdog.frameworks.db.ListPage;

import wx.weixin.bean.Weixinfuwumenu;

@Service
public class WeixinfuwumenuDao extends BaseDao<Weixinfuwumenu> {
	public static String tablename = "weixinfuwumenu";
	public static String selects = " * ";
	public static final int DORDERASC = 1;
	public static final int DORDERDESC = 2;

	public ListPage<Weixinfuwumenu> list(Weixinfuwumenu weixinfuwumenu,
			int page, int pageSize) {
		return list(weixinfuwumenu, page, pageSize, DORDERDESC);
	}

	public List<Weixinfuwumenu> list(Weixinfuwumenu weixinfuwumenu) {
		return list(weixinfuwumenu, DORDERDESC);
	}

	public ListPage<Weixinfuwumenu> list(Weixinfuwumenu weixinfuwumenu,
			int page, int pageSize, int dorder) {
		weixinfuwumenu.setAvailable(1);
		List<Object> params = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		sqlListAppender("available", where, weixinfuwumenu.getAvailable(),
				params);
		sqlListAppender("dorder", where, weixinfuwumenu.getDorder(), params);
		sqlListAppender("id", where, weixinfuwumenu.getId(), params);
		sqlListAppender("name", where, weixinfuwumenu.getName(), params);
		sqlListAppender("pid", where, weixinfuwumenu.getPid(), params);
		sqlListAppender("url", where, weixinfuwumenu.getUrl(), params);
		sqlListAppender("wtype", where, weixinfuwumenu.getWtype(), params);
		String wh = "";
		if (params.size() > 0) {
			wh = " where " + where.toString();
		}
		String orderby = "";
		if (dorder == DORDERASC) {
			orderby = " order by dorder asc ";
		} else {
			orderby = " order by dorder desc ";
		}
		String sql = " select " + selects + " from " + tablename + wh + orderby;
		String sqlCount = " select count(0) from " + tablename + wh;
		return listPage(sql, sqlCount, page, pageSize, params.toArray());
	}

	public List<Weixinfuwumenu> list(Weixinfuwumenu weixinfuwumenu, int dorder) {
		weixinfuwumenu.setAvailable(1);
		List<Object> params = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		sqlListAppender("available", where, weixinfuwumenu.getAvailable(),
				params);
		sqlListAppender("id", where, weixinfuwumenu.getId(), params);
		sqlListAppender("name", where, weixinfuwumenu.getName(), params);
		sqlListAppender("pid", where, weixinfuwumenu.getPid(), params);
		sqlListAppender("url", where, weixinfuwumenu.getUrl(), params);
		sqlListAppender("wtype", where, weixinfuwumenu.getWtype(), params);
		String wh = "";
		if (params.size() > 0) {
			wh = " where " + where.toString();
		}
		String orderby = "";
		if (dorder == DORDERASC) {
			orderby = " order by dorder asc ";
		} else {
			orderby = " order by dorder desc ";
		}
		String sql = " select " + selects + " from " + tablename + wh + orderby;
		return this.queryForBeanList(sql, params.toArray());
	}

	public Weixinfuwumenu findByFields(Weixinfuwumenu weixinfuwumenu) {
		weixinfuwumenu.setAvailable(1);
		List<Object> params = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		sqlListAppender("available", where, weixinfuwumenu.getAvailable(),
				params);
		sqlListAppender("dorder", where, weixinfuwumenu.getDorder(), params);
		sqlListAppender("id", where, weixinfuwumenu.getId(), params);
		sqlListAppender("name", where, weixinfuwumenu.getName(), params);
		sqlListAppender("pid", where, weixinfuwumenu.getPid(), params);
		sqlListAppender("url", where, weixinfuwumenu.getUrl(), params);
		sqlListAppender("wtype", where, weixinfuwumenu.getWtype(), params);
		String wh = "";
		if (params.size() > 0) {
			wh = " where " + where.toString();
		}
		String orderby = "";
		String sql = " select " + selects + " from " + tablename + wh + orderby;
		return this.queryForBean(sql, params.toArray());
	}

	public StringBuffer append(StringBuffer where, String sql) {
		if (!where.toString().equals("")) {
			where.append(" and ");
		}
		where.append(sql);
		return where;
	}

	public int countByFields(Weixinfuwumenu weixinfuwumenu) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		sqlListAppender("available", where, weixinfuwumenu.getAvailable(),
				params);
		sqlListAppender("dorder", where, weixinfuwumenu.getDorder(), params);
		sqlListAppender("id", where, weixinfuwumenu.getId(), params);
		sqlListAppender("name", where, weixinfuwumenu.getName(), params);
		sqlListAppender("pid", where, weixinfuwumenu.getPid(), params);
		sqlListAppender("url", where, weixinfuwumenu.getUrl(), params);
		sqlListAppender("wtype", where, weixinfuwumenu.getWtype(), params);
		String wh = "";
		if (params.size() > 0) {
			wh = " where " + where.toString();
			String sql = " select  count(0) from " + tablename + wh;
			return DB.queryForInt(sql, params.toArray());
		} else {
			String sql = " select  count(0) from " + tablename;
			return DB.queryForInt(sql);
		}
	}

	public void updateByFields(Weixinfuwumenu weixinfuwumenu,
			Weixinfuwumenu dwhere) {
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		String updates = "";
		updates = sqlUpdateAppender("available", updates,
				weixinfuwumenu.getAvailable(), params);
		updates = sqlUpdateAppender("dorder", updates,
				weixinfuwumenu.getDorder(), params);
		updates = sqlUpdateAppender("id", updates, weixinfuwumenu.getId(),
				params);
		updates = sqlUpdateAppender("name", updates, weixinfuwumenu.getName(),
				params);
		updates = sqlUpdateAppender("pid", updates, weixinfuwumenu.getPid(),
				params);
		updates = sqlUpdateAppender("url", updates, weixinfuwumenu.getUrl(),
				params);
		updates = sqlUpdateAppender("wtype", updates,
				weixinfuwumenu.getWtype(), params);

		sqlListAppender("available", where, weixinfuwumenu.getAvailable(),
				params);
		sqlListAppender("dorder", where, weixinfuwumenu.getDorder(), params);
		sqlListAppender("id", where, weixinfuwumenu.getId(), params);
		sqlListAppender("name", where, weixinfuwumenu.getName(), params);
		sqlListAppender("pid", where, weixinfuwumenu.getPid(), params);
		sqlListAppender("url", where, weixinfuwumenu.getUrl(), params);
		sqlListAppender("wtype", where, weixinfuwumenu.getWtype(), params);
		String wh = "";
		if (params.size() > 0) {
			wh = " where " + where.toString();
		}
		String sql = " update weixinfuwumenu set " + updates + wh;
		DB.update(sql, params.toArray());
	}

	public void updateByPriKey(Weixinfuwumenu weixinfuwumenu) {
		List<Object> params = new ArrayList<Object>();
		String updates = "";
		updates = sqlUpdateAppender("available", updates,
				weixinfuwumenu.getAvailable(), params);
		updates = sqlUpdateAppender("dorder", updates,
				weixinfuwumenu.getDorder(), params);
		updates = sqlUpdateAppender("name", updates, weixinfuwumenu.getName(),
				params);
		updates = sqlUpdateAppender("pid", updates, weixinfuwumenu.getPid(),
				params);
		updates = sqlUpdateAppender("url", updates, weixinfuwumenu.getUrl(),
				params);
		updates = sqlUpdateAppender("wtype", updates,
				weixinfuwumenu.getWtype(), params);
		if (params.size() == 0) {
			return;
		}
		params.add(weixinfuwumenu.getId());
		String sql = " update weixinfuwumenu set " + updates + " where id= ?";
		DB.update(sql, params.toArray());
	}

	public void save(Weixinfuwumenu weixinfuwumenu) {
		weixinfuwumenu.setAvailable(1);
		List<Object> params = new ArrayList<Object>();
		String values[] = { "", "" };
		sqlSaveAppender("available", values, weixinfuwumenu.getAvailable(),
				params);
		sqlSaveAppender("dorder", values, getMaxDorder(), params);
		sqlSaveAppender("name", values, weixinfuwumenu.getName(), params);
		sqlSaveAppender("pid", values, weixinfuwumenu.getPid(), params);
		sqlSaveAppender("url", values, weixinfuwumenu.getUrl(), params);
		sqlSaveAppender("wtype", values, weixinfuwumenu.getWtype(), params);
		if (params.size() == 0) {
			return;
		}
		String sql = "insert into weixinfuwumenu (" + values[0] + ") values("
				+ values[1] + ")";
		DB.update(sql, params.toArray());
	}

	public int getMaxDorder() {
		String sql = "select max(id) from " + tablename;
		return DB.queryForInt(sql) + 1;
	}

	public Weixinfuwumenu findByAvailable(Integer available) {
		String sql = " select " + selects + " from " + tablename
				+ " where available = ? and available=?";
		return this.queryForBean(sql, available, 1);
	}

	public Weixinfuwumenu findByDorder(Integer dorder) {
		String sql = " select " + selects + " from " + tablename
				+ " where dorder = ? and available=?";
		return this.queryForBean(sql, dorder, 1);
	}

	public Weixinfuwumenu findById(Integer id) {
		String sql = " select " + selects + " from " + tablename
				+ " where id = ? and available=?";
		return this.queryForBean(sql, id, 1);
	}

	public Weixinfuwumenu findByName(String name) {
		String sql = " select " + selects + " from " + tablename
				+ " where name = ? and available=?";
		return this.queryForBean(sql, name, 1);
	}

	public Weixinfuwumenu findByPid(Integer pid) {
		String sql = " select " + selects + " from " + tablename
				+ " where pid = ? and available=?";
		return this.queryForBean(sql, pid, 1);
	}

	public Weixinfuwumenu findByUrl(String url) {
		String sql = " select " + selects + " from " + tablename
				+ " where url = ? and available=?";
		return this.queryForBean(sql, url, 1);
	}

	public Weixinfuwumenu findByWtype(String wtype) {
		String sql = " select " + selects + " from " + tablename
				+ " where wtype = ? and available=?";
		return this.queryForBean(sql, wtype, 1);
	}

	public void deleteById(Weixinfuwumenu weixinfuwumenu) {
		String sql = " update " + tablename + " set available=? where id = ?";
		DB.update(sql, 0, weixinfuwumenu.getId());
	}

	private StringBuffer sqlListAppender(String field, StringBuffer sql,
			Object obj, List<Object> params) {
		if (obj instanceof String) {
			if (obj != null && !obj.equals("")) {
				sql = this.append(sql, field + "=?");
				params.add(obj);
			}
		} else {
			if (obj != null) {
				sql = this.append(sql, field + "=?");
				params.add(obj);
			}
		}
		return sql;
	}

	private String sqlUpdateAppender(String field, String sql, Object obj,
			List<Object> params) {
		if (obj != null) {
			if (params.size() == 0) {
				sql = field + "=?";
			} else {
				sql = sql + "," + field + "=?";
			}
			params.add(obj);
		}
		return sql;
	}

	private String[] sqlSaveAppender(String field, String[] values, Object obj,
			List<Object> params) {
		if (obj != null) {
			if (params.size() > 0) {
				values[0] = values[0] + "," + field;
				values[1] = values[1] + "," + "?";
			} else {
				values[0] = field;
				values[1] = "?";
			}
			params.add(obj);
		}
		return values;
	}

}
