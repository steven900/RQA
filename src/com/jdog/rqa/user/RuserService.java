package com.jdog.rqa.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.ListPage;

@Service
public class RuserService {

	@Resource
	private RuserDao ruserDao;
	public ListPage<Ruser> list(Ruser t, int page, int pageSize) {
		
		return ruserDao.list(t,page,pageSize);
	}
	public void save(Ruser t) {
		ruserDao.save(t);
	}
	
	public void delete(int id) {
		ruserDao.delete(id);
	}
	public Ruser findById(int id) {
		return ruserDao.findById(id);
	}

}
