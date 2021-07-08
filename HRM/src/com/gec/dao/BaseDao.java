package com.gec.dao;

import java.util.List;

import com.gec.util.PageModel;

public interface BaseDao<T> {
	/*
	 * 分页查询
	 */
	public PageModel<T> findByPage(int pageIndex,T entity);
	/*
	 * 查询id
	 */
	public T findById(Integer id);
	/*
	 * 添加
	 */
	public boolean save(T entity);
	/*
	 * 跟新
	 */
	public boolean update(T entity);
	/*
	 * 删除
	 */
	public boolean del(String id);
	
	/*
	 * 查询所有
	 */
	public List<T> findAll();
}
