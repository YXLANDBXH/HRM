package com.gec.dao;

import com.gec.bean.Employee;

public interface EmployeeDao extends BaseDao<Employee> {
	//查询身份证号
	Employee findCardId(String cardId);
	//查询手机号码
	Employee findPhone(String phone);
	//查询电话号码
	Employee findTel(String tel);
	//查询qq号码
	Employee findqqNum(String qqNum);
}
