package com.gec.service.impl;

import java.util.List;

import com.gec.bean.Types;
import com.gec.dao.TypesDao;
import com.gec.dao.impl.TypeDaoImpl;
import com.gec.service.TypesService;
import com.gec.util.PageModel;

public class TypesServiceImpl implements TypesService {
	TypesDao td = new TypeDaoImpl();
	@Override
	public PageModel<Types> findByPage(int pageIndex, Types entity) {
		return td.findByPage(pageIndex, entity);
	}

	@Override
	public Types findById(Integer id) {
		return td.findById(id);
	}

	@Override
	public boolean save(Types entity) {
		return td.save(entity);
	}

	@Override
	public boolean update(Types entity) {
		return td.update(entity);
	}

	@Override
	public List<Types> findAll() {
		return td.findAll();
	}
	//公告类型删除（可多个）
	@Override
	public boolean del(String[] id) {
		try {
			for (int i = 0; i < id.length; i++) {
				td.del(id[i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
