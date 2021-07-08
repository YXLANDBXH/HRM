package com.gec.service;

import java.util.List;

import com.gec.bean.Types;
import com.gec.util.PageModel;

public interface TypesService {
	/*
	 * 公告类型分页查询
	 */
	public PageModel<Types> findByPage(int pageIndex,Types entity);
	
	public Types findById(Integer id);
	/*
	 * 公告类型添加
	 */
	public boolean save(Types entity);
	/*
	 * 
	 */
	public boolean update(Types entity);
	
	//查询所有
	public List<Types> findAll();

	public boolean del(String[] id);
}
