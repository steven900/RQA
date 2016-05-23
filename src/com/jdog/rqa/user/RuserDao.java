package com.jdog.rqa.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;
import com.jdog.frameworks.db.ListPage;

@Service
public class RuserDao extends BaseDao<Ruser> {

	private static String selects = " * ";
	private static String tablename = " ruser";

	public ListPage<Ruser> list(Ruser t, int page, int pageSize) {
		String orderby = " order by id desc ";
		String wh = " where available =1 and type=? and projectid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(t.getType());
		params.add(t.getProjectid());
		String sql = " select " + selects + " from " + tablename + wh + orderby;
		String sqlCount = " select count(0) from " + tablename + wh;
		return listPage(sql, sqlCount, page, pageSize, params.toArray());
	}

	public void save(Ruser t) {
		String sql = "insert into ruser (title,url,addtime,available,type,projectid,shortphoto,brief) values(?,?,?,1,?,?,?,?)";
		DB.insert(sql, t.getTitle(),t.getUrl(),t.getAddtime(),t.getType(),t.getProjectid(),t.getShortphoto(),t.getBrief());
	}

	public Ruser findById(int id) {
		String sql = "select * from ruser where id = ? and available =1";
		return this.queryForBean(sql, id);
	}

}
