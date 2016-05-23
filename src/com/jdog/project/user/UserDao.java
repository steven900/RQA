package com.jdog.project.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;
import com.jdog.frameworks.db.ListPage;

@Service
public class UserDao extends BaseDao<User> {

	
	private static String selects = " * ";
	private static String tablename = " user ";

	public ListPage<User> list(User t, int page, int pageSize) {
		String orderby = " order by id desc ";
		String wh = " where available =1 ";
		List<Object> params = new ArrayList<Object>();
		String sql = " select " + selects + " from " + tablename + wh + orderby;
		String sqlCount = " select count(0) from " + tablename + wh;
		return listPage(sql, sqlCount, page, pageSize, params.toArray());
	}

	public User getById(int id) {
		String sql = "select * from user where id = ? and available=1";
		return this.queryForBean(sql, id);
	}

	public void save(User t) {
		String sql = "insert into user(name,username,password,available,photo,pass) values(?,?,?,1,?,?)";
		 DB.insert(sql, t.getName(),t.getUsername(),t.getPassword(),t.getPhoto(),t.getPass());
	}

	public void updateB(User t) {
		String sql = "update user set name=?,username=?,password=?,photo=?,pass=? where id=? and available=1";
		if(t.getName()==null)
			t.setName("");
		if(t.getUsername()==null)
			t.setUsername("");
		if(t.getPhoto()==null)
			t.setPhoto("");
		
		 DB.update(sql, t.getName(),t.getUsername(),t.getPassword(),t.getPhoto(),t.getPass(),t.getId());
	}

	public User findByUser(String username, String password) {
		String sql = "select * from user where username=? and password=? and available=1";
		return this.queryForBean(sql, username,password);
	}

	public List<User> listAll(int projectid) {
		String sql = "SELECT u.* FROM USER u LEFT JOIN menuauth m ON u.id = m.userid WHERE m.projectid = ? AND available =1;";
		return this.queryForBeanList(sql,projectid);
	}

	public User findById(int uid) {
		String sql = "select * from user where available=1 and id= ?";
		return this.queryForBean(sql, uid);
	}
}
