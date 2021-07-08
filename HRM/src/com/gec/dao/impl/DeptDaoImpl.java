package com.gec.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.bean.Dept;
import com.gec.dao.DeptDao;
import com.gec.util.DBUtil;
import com.gec.util.PageModel;

public class DeptDaoImpl extends DBUtil<Dept> implements DeptDao {
	/*
	 * 部门分页查询和模糊查询
	 */
	@Override
	public PageModel<Dept> findByPage(int pageIndex, Dept entity) {
		PageModel<Dept> pageModel = new PageModel<>();
		List<Object> obj = new ArrayList<>();
		pageModel.setPageIndex(pageIndex);
		String sql ="select count(id) from dept_inf where 1=1 ";  //模糊查询
		String sql1 = "select * from dept_inf where 1=1 ";   //查询所有
		if (entity.getName()!=null&&!entity.getName().equals("")) { 
			sql += "and name like ?";
			sql1 += "and name like ?";
			obj.add("%"+entity.getName()+"%");
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

	@Override
	public Dept findById(Integer id) {
		List<Dept> list = query("select * from dept_inf where id = ?",id);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 部门添加
	 */
	@Override
	public boolean save(Dept entity) {
		return update("insert into dept_inf values(null,?,?,0)",entity.getName(),entity.getRemark());
	}
	/*
	 * 部门修改
	 */
	@Override
	public boolean update(Dept entity) {
		return update("update dept_inf set name=?,remark=? where id=?", entity.getName(),entity.getRemark(),entity.getId());
	}
	/*
	 * 部门删除
	 */
	@Override
	public boolean del(String id) {
		return update("delete from dept_inf where id = ?", id);
	}

	@Override
	public List<Dept> findAll() {
		return query("select * from dept_inf");
	}

	@Override
	public Dept getEntity(ResultSet rs) throws Exception {
		Dept dept = new Dept();
		dept.setId(rs.getInt(1));
		dept.setName(rs.getString(2));
		dept.setRemark(rs.getString(3));
		return dept;
	}

}
