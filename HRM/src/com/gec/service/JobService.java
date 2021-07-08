package com.gec.service;

import java.util.List;

import com.gec.bean.Job;
import com.gec.util.PageModel;

public interface JobService {
	//分页查询
	public PageModel<Job> findByPage(int pageIndex,Job entity);
	
	public Job findById(Integer id);
	//职位添加
	public boolean save(Job entity);
	//职位修改
	public boolean update(Job entity);
	//职位删除
	public boolean del(String[] ids);

	public List<Job> findALL();
}
