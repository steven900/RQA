package com.jdog.project.bugmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;
import com.jdog.frameworks.db.ListPage;

@Service
public class BugDao extends BaseDao<Bug>{

	public String tablename = " bug ";
	public ListPage<Bug> list(Bug t, int page, int pageSize) {
		String orderby = " order by id desc ";
		String wh = " where available =1 and projectid = ? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(t.getProjectid());
		if(t.getState() != -1){
			wh = wh + " and state = ? ";
			params.add(t.getState());
		}
		if(t.getSentTo()!=0){
			wh = wh + "  and sentto=?";
			params.add(t.getSentTo());
		}
	
		String sql = " select * from " + tablename + wh + orderby;
		String sqlCount = " select count(0) from " + tablename + wh;
		return listPage(sql, sqlCount, page, pageSize, params.toArray());
	}
	
	public ListPage<Bug> list2(Bug t, int page, int pageSize) {
		String orderby = " order by state  ";
		String wh = " where available =1 and projectid = ? and state<>1 ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(t.getProjectid());
	
		String sql = " select * from " + tablename + wh + orderby;
		String sqlCount = " select count(0) from " + tablename + wh;
		return listPage(sql, sqlCount, page, pageSize, params.toArray());
	}
	
	
	public void save(Bug t) {
		String sql = "insert into bug (title,brief,available,pic,state,sentToUser,sentto,projectid,addtime) values(?,?,1,?,?,?,?,?,?)";
		DB.insert(sql, t.getTitle(),t.getBrief(),t.getPic(),t.getState(),t.getSentToUser(),t.getSentTo(),t.getProjectid(),new Date());
	}

	public Bug findById(int id) {
		String sql = "select * from bug where id = ? and available =1";
		return this.queryForBean(sql, id);
	}

	public void updateB(Bug t) {
		String sql = "update bug set state = ? where id=? and available=1";
		DB.update(sql, t.getState(),t.getId());
	}

	public void updateBug(Bug t) {
		String sql = "update bug set state = ?,title=?,brief=?,pic=?,sentToUser=?,projectid=?,sentto=? where id=? and available=1";
		DB.update(sql, t.getState(),t.getTitle(),t.getBrief(),t.getPic(),t.getSentToUser(),t.getProjectid(),t.getSentTo(),t.getId());
	}

	public int countXf(int userid,int state,int projectid ) {
		if(projectid ==0){
		String sql =" select count(0) from bug where available=1 and sentto=? and state =?";
		return DB.queryForInt(sql, userid,state);
		}
		else{
			String sql =" select count(0) from bug where available=1 and sentto=? and state =? and projectid = ?";
			return DB.queryForInt(sql, userid,state,projectid);
		}
	}

	
	
}
