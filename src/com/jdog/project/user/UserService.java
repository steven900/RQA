package com.jdog.project.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.ListPage;

@Service
public class UserService {

	@Resource
	private UserDao userDao;
	
	public ListPage<User> list(User t, int page, int pageSize) {
		
		return userDao.list(t,page,pageSize);
	}

	public User findById(int id) {
		
		return userDao.getById(id);
	}

	public void save(User t) {
		
		 userDao.save(t);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public void updateB(User t) {
		userDao.updateB(t);
		
	}
	
}
