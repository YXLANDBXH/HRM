package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gec.bean.User;
import com.gec.dao.UserDao;
import com.gec.util.DBUtil;
import com.gec.util.PageModel;

public class UserDaoImpl extends DBUtil<User> implements UserDao {
	/*
	 * 用户分页查询
	 */
	@Override
	public PageModel<User> findByPage(int pageIndex, User entity) {
		PageModel<User> pageModel = new PageModel<>();
		List<Object> obj = new ArrayList<>();
		pageModel.setPageIndex(pageIndex);
		String sql ="select count(id) from user_inf where 1=1 ";
		String sql1 = "select * from user_inf where 1=1 ";
		if (entity.getLoginname()!=null && !entity.getLoginname().equals("")) {
			sql += "and loginname like ? ";
			sql1 += "and loginname like ? ";
			obj.add("%"+entity.getLoginname()+"%");
		}
		if(entity.getUsername()!=null && !entity.getUsername().equals("")){
			sql += "and username like ? ";
			sql1 += "and username like ? ";
			obj.add("%"+entity.getUsername()+"%");
		}
		if(entity.getStatus()!=null && !entity.getStatus().equals("")){
			sql += "and status=? ";
			sql1 += "and status=? ";
			obj.add(entity.getStatus());
		}

		pageModel.setTotalRecordSum(getCount(sql, obj.toArray()));
		if (pageModel.getTotalPageSum() < pageIndex) {
			pageIndex = 1;
		}
		sql1 += "limit ?,?";
		obj.add((pageIndex-1)*PageModel.getPagesize());
		obj.add(PageModel.getPagesize());
		pageModel.setList(query(sql1, obj.toArray()));
		return pageModel;
	}
	
	/*
	 * 查找用户id
	 */
	@Override
	public User findById(Integer id) {
		List<User> list = query("select * from user_inf where id = ?",id);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 用户添加
	 */
	@Override
	public boolean save(User entity) {
		return update("insert into user_inf values(null,?,?,?,null,?)", entity.getLoginname(),entity.getPassword(),entity.getStatus(),entity.getUsername());
	}
	/*
	 * 用户跟新
	 */
	@Override
	public boolean update(User entity) {
		return update("update user_inf set loginname = ?,password=?,username=?,status=? where id=?", entity.getLoginname(),entity.getPassword(),entity.getUsername(),entity.getStatus(),entity.getId());
	}

	@Override
	public List<User> findAll() {
		return null;
	}
	/*
	 * 用户登录
	 */
	@Override
	public User login(String name, String password) {
		List<User> list = query("select * from user_inf where loginname=? and password=?", name,password);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	/*
	 * 用户删除
	 */
	@Override
	public boolean del(String id) {
		return update("delete from user_inf where id = ?",id);
	}
	
	@Override
	public User getEntity(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(1));
		user.setLoginname(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setStatus(rs.getInt(4));
		user.setCreatedate(rs.getDate(5));
		user.setUsername(rs.getString(6));
		return user;
	}

	@Override
	public User findLoginName(String loginname) {
		List<User> list = query("select * from user_inf where loginname = ?", loginname);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
