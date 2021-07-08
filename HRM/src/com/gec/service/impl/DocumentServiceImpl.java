package com.gec.service.impl;

import java.util.List;

import com.gec.bean.Document;
import com.gec.bean.Notice;
import com.gec.dao.DocumentDao;
import com.gec.dao.NoticeDao;
import com.gec.dao.impl.DocumentDaoImpl;
import com.gec.dao.impl.NoticeDaoImpl;
import com.gec.service.DocumentService;
import com.gec.service.NoticeService;
import com.gec.util.PageModel;

public class DocumentServiceImpl implements DocumentService {
	DocumentDao dd = new DocumentDaoImpl();
	//文档删除（可多个）
	@Override
	public boolean del(String[] id) {
		try {
			for (int i = 0; i < id.length; i++) {
				dd.del(id[i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public PageModel<Document> findByPage(int pageIndex, Document entity) {
		return dd.findByPage(pageIndex, entity);
	}

	@Override
	public Document findById(Integer id) {
		return dd.findById(id);
	}

	@Override
	public boolean save(Document entity) {
		return dd.save(entity);
	}

	@Override
	public boolean update(Document entity) {
		return dd.update(entity);
	}

	@Override
	public List<Document> findAll() {
		return dd.findAll();
	}

}
