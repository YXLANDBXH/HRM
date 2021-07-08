package com.gec.service;

import java.util.List;

import com.gec.bean.Dept;
import com.gec.util.PageModel;

public interface DeptService {
	//分页查询
	public PageModel<Dept> findByPage(int pageIndex,Dept entity);
	
	public Dept findById(Integer id);
	//部门添加
	public boolean save(Dept entity);
	//部门更新
	public boolean update(Dept entity);
	
	//查询所有
	public List<Dept> findAll();

	public boolean del(String[] id);
}
