package com.gec.service.impl;

import java.util.List;

import com.gec.bean.Employee;
import com.gec.dao.EmployeeDao;
import com.gec.dao.impl.EmployeeDaoImpl;
import com.gec.service.EmployeeService;
import com.gec.util.PageModel;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDao ed = new EmployeeDaoImpl();
	@Override
	public PageModel<Employee> findByPage(int pageIndex, Employee entity) {
		return ed.findByPage(pageIndex, entity);
	}

	@Override
	public Employee findById(Integer id) {
		return ed.findById(id);
	}

	@Override
	public boolean save(Employee entity) {
		return ed.save(entity);
	}

	@Override
	public boolean update(Employee entity) {
		return ed.update(entity);
	}

	@Override
	public List<Employee> findAll() {
		return ed.findAll();
	}
	//员工删除（可多个）
	@Override
	public boolean del(String[] id) {
		try {
			for (int i = 0; i < id.length; i++) {
				ed.del(id[i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Employee findCardId(String cardId) {
		return ed.findCardId(cardId);
	}

	@Override
	public Employee findPhone(String phone) {
		return ed.findPhone(phone);
	}
	/*
	 * 查询电话号码
	 */
	@Override
	public Employee findTel(String tel) {
		return ed.findTel(tel);
	}

	@Override
	public Employee findqqNum(String qqNum) {
		return ed.findqqNum(qqNum);
	}

}
