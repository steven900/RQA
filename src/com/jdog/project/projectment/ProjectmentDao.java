package com.jdog.project.projectment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;
import com.jdog.frameworks.db.ListPage;

@Service
public class ProjectmentDao extends BaseDao<Projectment>{

	
	public String tablename = " projectment ";
	
	public ListPage<Projectment> list(Projectment t, int page, int pageSize) {
		String orderby = " order by id desc ";
		String wh = " where available =1  ";
		List<Object> params = new ArrayList<Object>();
		String sql = " select * from " + tablename + wh + orderby;
		String sqlCount = " select count(0) from " + tablename + wh;
		return listPage(sql, sqlCount, page, pageSize, params.toArray());
	}

	public Projectment findById(int id) {
		String sql = "select * from projectment where id = ? and available =1";
		return this.queryForBean(sql, id);
	}

	public void updateBug(Projectment t) {
		String sql = "update projectment set title=?,brief=?,contact=? where id=? and available=1";
		DB.update(sql, t.getTitle(),t.getBrief(),t.getContact(),t.getId());
		
	}

	public void save(Projectment t) {
		String sql = "insert into projectment (title,brief,available,addtime,contact) values(?,?,1,?,?)";
		DB.insert(sql, t.getTitle(),t.getBrief(),new Date(),t.getContact());
		
	}
}
