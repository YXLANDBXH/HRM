package com.gec.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.bean.Job;
import com.gec.dao.JobDao;
import com.gec.util.DBUtil;
import com.gec.util.PageModel;

public class JobDaoImpl extends DBUtil<Job> implements JobDao {
	/*
	 * 职位分页查询和模糊查询
	 */
	@Override
	public PageModel<Job> findByPage(int pageIndex, Job entity) {
		PageModel<Job> pageModel = new PageModel<>();
		List<Object> obj = new ArrayList<>();
		pageModel.setPageIndex(pageIndex);
		String sql ="select count(id) from job_inf where 1=1 ";
		String sql1 = "select * from job_inf where 1=1 ";     //模糊查询
		if(entity.getId()!=null&&!entity.getId().equals("")) {
			sql += "and id = ?";
			sql1 += "and id = ?";
			obj.add(entity.getId());
		}
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
	public Job findById(Integer id) {
		List<Job> list = query("select * from job_inf where id = ?",id);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 职位添加
	 */
	@Override
	public boolean save(Job entity) {
		return update("insert into job_inf values(null,?,?,0)", entity.getName(),entity.getRemark());
	}
	/*
	 * 职位跟新
	 */
	@Override
	public boolean update(Job entity) {
		return update("update job_inf set name=?,remark=? where id=?", entity.getName(),entity.getRemark(),entity.getId());
	}
	/*
	 * 职位删除
	 */
	@Override
	public boolean del(String id) {
		return update("delete from job_inf where id = ?", id);
	}

	@Override
	public List<Job> findAll() {
		return query("select * from job_inf");
	}

	@Override
	public Job getEntity(ResultSet rs) throws Exception {
		Job j = new Job();
		j.setId(rs.getInt(1));
		j.setName(rs.getString(2));
		j.setRemark(rs.getString(3));
		return j;
	}

}
