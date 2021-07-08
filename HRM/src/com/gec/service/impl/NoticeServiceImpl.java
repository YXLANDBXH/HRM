package com.gec.service.impl;

import java.util.List;

import com.gec.bean.Notice;
import com.gec.dao.NoticeDao;
import com.gec.dao.impl.NoticeDaoImpl;
import com.gec.service.NoticeService;
import com.gec.util.PageModel;

public class NoticeServiceImpl implements NoticeService {
	NoticeDao nd = new NoticeDaoImpl();
	@Override
	public PageModel<Notice> findByPage(int pageIndex, Notice entity) {
		return nd.findByPage(pageIndex, entity);
	}

	@Override
	public Notice findById(Integer id) {
		return nd.findById(id);
	}

	@Override
	public boolean save(Notice entity) {
		return nd.save(entity);
	}

	@Override
	public boolean update(Notice entity) {
		return nd.update(entity);
	}

	@Override
	public List<Notice> findAll() {
		return nd.findAll();
	}
	//公告删除（可多个）
	@Override
	public boolean del(String[] id) {
		try {
			for (int i = 0; i < id.length; i++) {
				nd.del(id[i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
