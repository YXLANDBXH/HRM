package com.gec.service.impl;

import com.gec.bean.User;
import com.gec.dao.UserDao;
import com.gec.dao.impl.UserDaoImpl;
import com.gec.service.UserService;
import com.gec.util.PageModel;

public class UserServiceImpl implements UserService {

	UserDao ud = new UserDaoImpl();
	@Override
	public PageModel<User> findByPage(int pageIndex, User entity) {
		return ud.findByPage(pageIndex, entity);
	}

	@Override
	public User findById(Integer id) {
		return ud.findById(id);
	}

	@Override
	public boolean save(User entity) {
		return ud.save(entity);
	}

	@Override
	public boolean update(User entity) {
		return ud.update(entity);
	}
	//用户删除（可多个）
	@Override
	public boolean del(String[] id) {
		try {
			for (int i = 0; i < id.length; i++) {
				ud.del(id[i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public User login(String loginname, String password) {
		return ud.login(loginname, password);
	}

	@Override
	public User findLoginName(String loginname) {
		return ud.findLoginName(loginname);
	}

}
