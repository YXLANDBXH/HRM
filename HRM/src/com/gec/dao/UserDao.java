package com.gec.dao;

import com.gec.bean.User;

public interface UserDao extends BaseDao<User> {
	/*
	 * 登录
	 */
	public User login(String name,String password);
	/*
	 * 查询登录名
	 */
	public User findLoginName(String loginname);
}
