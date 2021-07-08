package com.gec.service;

import com.gec.bean.User;
import com.gec.util.PageModel;

public interface UserService {
	/*
	 * 用户分页查询
	 */
	public PageModel<User> findByPage(int pageIndex,User entity);
	
	public User findById(Integer id);
	/*
	 * 用户添加
	 */
	public boolean save(User entity);
	/*
	 * 用户跟新
	 */
	public boolean update(User entity);
	/*
	 * 用户删除
	 */
	public boolean del(String[] ids);
	/*
	 * 用户登录
	 */
	public User login(String loginname,String password);
	/*
	 * 查询登录名
	 */
	public User findLoginName(String loginname);
}
