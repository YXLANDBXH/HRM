package com.gec.service;

import java.util.List;

import com.gec.bean.Document;
import com.gec.util.PageModel;

public interface DocumentService {
	//分页查询
	public PageModel<Document> findByPage(int pageIndex,Document entity);
	
	public Document findById(Integer id);
	
	public boolean save(Document entity);
	
	public boolean update(Document entity);
	
	//查询所有
	public List<Document> findAll();

	public boolean del(String[] id);
}
