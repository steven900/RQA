package com.jdog.project.pricereport;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;

@Service
public class PricereportDao extends BaseDao<Pricereport> {
	
	
	public void save(int projectid,int priceid){
		String sql =" insert into pricereport (projectid,priceid) values(?,?)";
		DB.insert(sql, projectid,priceid);
	}
	
	public void delete(int projectid,int priceid){
		String sql ="delete from pricereport where projectid=? and priceid=?";
		DB.update(sql, projectid,priceid);
	}
	
	public Pricereport findBy(int projectid,int priceid){
		String sql = "select * from pricereport where projectid=? and priceid=?";
		return this.queryForBean(sql, projectid,priceid);
	}
	
	public List<Pricereport> listReport(int projectid){
		String sql = "select * from pricereport where projectid=? ";
		return this.queryForBeanList(sql, projectid);
	}

	

}
