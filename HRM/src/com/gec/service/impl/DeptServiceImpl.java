package com.gec.service.impl;

import java.util.List;

import com.gec.bean.Dept;
import com.gec.dao.DeptDao;
import com.gec.dao.impl.DeptDaoImpl;
import com.gec.service.DeptService;
import com.gec.util.PageModel;

public class DeptServiceImpl implements DeptService {

	DeptDao dd = new DeptDaoImpl();
	@Override
	public PageModel<Dept> findByPage(int pageIndex, Dept entity) {
		return dd.findByPage(pageIndex, entity);
	}

	@Override
	public Dept findById(Integer id) {
		return dd.findById(id);
	}

	@Override
	public boolean save(Dept entity) {
		return dd.save(entity);
	}

	@Override
	public boolean update(Dept entity) {
		return dd.update(entity);
	}

	@Override
	public List<Dept> findAll() {
		return dd.findAll();
	}
	//部门删除（可多个）
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

}
