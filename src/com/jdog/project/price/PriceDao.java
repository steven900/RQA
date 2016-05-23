package com.jdog.project.price;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;
import com.jdog.frameworks.db.ListPage;

@Service
public class PriceDao extends BaseDao<Price>{

	public String tablename=" price ";
	public ListPage<Price> list(Price t, int page, int pageSize) {
		
		String orderby = " order by id desc ";
		String wh = " where available =1 ";
		
		List<Object> params = new ArrayList<Object>();
	
		String sql = " select * from " + tablename + wh + orderby;
		String sqlCount = " select count(0) from " + tablename + wh;
		return listPage(sql, sqlCount, page, pageSize, params.toArray());
	}
	
	
	public void save(Price t) {
		if(t!=null){
		String sql = "insert into price (title,url,price,days,available) values(?,?,?,?,1)";
		DB.insert(sql,t.getTitle(),t.getUrl(),t.getPrice(),t.getDays());
		}
	}


	public Price getById(int priceid) {
		String sql = "select * from price where id = ?";
		return this.queryForBean(sql, priceid);
	}


	public void updatePrice(Price t) {
		String sql = "update price (title,url,price,days) values(?,?,?,?)";
		DB.update(sql, t.getTitle(),t.getUrl(),t.getPrice(),t.getDays());
	}



	
	

}
