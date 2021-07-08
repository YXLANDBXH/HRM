package com.gec.service;

import java.util.List;
import com.gec.bean.Notice;
import com.gec.util.PageModel;

public interface NoticeService {
	//分页查询
	public PageModel<Notice> findByPage(int pageIndex,Notice entity);
	
	public Notice findById(Integer id);
	/*
	 * 公告添加
	 */
	public boolean save(Notice entity);
	/*
	 * 公告更新
	 */
	public boolean update(Notice entity);
	
	//查询所有
	public List<Notice> findAll();
	/*
	 * 公告删除
	 */
	public boolean del(String[] id);
}
