package com.jdog.project.menuauth;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.BaseDao;
import com.jdog.frameworks.db.DB;

@Service
public class MenuauthDao extends BaseDao<Menuauth> {

	public void updateRqa(int projectid, int rqa,int userid) {
		String sql = " select id from menuauth where projectid=? and userid=?";
		int flag =DB.queryForInt(sql, projectid,userid);
		if(flag == 0){
			String sql2 = "insert into menuauth (projectid,userid,rqa) values(?,?,?)";
			DB.insert(sql2, projectid,userid,rqa);
		}else{
			String sql3="update menuauth set rqa = ? where id=?";
			DB.update(sql3,rqa, flag);
		}
		
	}
	
	public boolean checkUserAuth(int proid,int userid){
		String sql = " select rqa from menuauth where projectid=? and userid=?";
		int flag =DB.queryForInt(sql, proid,userid);
		return flag==1;
	}

	public Menuauth findById(int projectid, int userid) {
		String sql ="select * from menuauth  where userid=? and projectid= ?";
		return this.queryForBean(sql, userid,projectid);
	}

	public void updateprice(int projectid, int checkid, int userid) {
		String sql = " select count(0) from menuauth where projectid=? and userid=?";
		int flag =DB.queryForInt(sql, projectid,userid);
		if(flag == 0){
			String sql2 = "insert into menuauth (projectid,userid,price) values(?,?,?)";
			DB.insert(sql2, projectid,userid,checkid);
		}else{
			String sql3="update menuauth set price = ? where id=?";
			DB.update(sql3,checkid, flag);
		}
	}
	
	

	public List<Menuauth> list(int userid) {
		String sql ="select * from menuauth m left join Projectment p on m.projectid = p.id where m.userid=? and m.rqa = 1 and p.available=1";
		return this.queryForBeanList(sql, userid);
	}

}
