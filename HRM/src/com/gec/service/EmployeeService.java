package com.gec.service;

import java.util.List;

import com.gec.bean.Employee;
import com.gec.util.PageModel;

public interface EmployeeService {
	/*
	 * 分页查询
	 */
	public PageModel<Employee> findByPage(int pageIndex,Employee entity);
	
	public Employee findById(Integer id);
	
	public boolean save(Employee entity);
	
	public boolean update(Employee entity);
	
	public boolean del(String[] id);
	//查询所有
	public List<Employee> findAll();
	//查找省份证
	public Employee findCardId(String cardId);
	//查找手机号码
	public Employee findPhone(String phone);
	//查询电话号码
	public Employee findTel(String tel);
	//查询qq号
	public Employee findqqNum(String qqNum);
}
