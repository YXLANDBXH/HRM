package com.gec.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.bean.Employee;
import com.gec.dao.EmployeeDao;
import com.gec.service.DeptService;
import com.gec.service.JobService;
import com.gec.service.impl.DeptServiceImpl;
import com.gec.service.impl.JobServiceImpl;
import com.gec.util.DBUtil;
import com.gec.util.PageModel;

public class EmployeeDaoImpl extends DBUtil<Employee> implements EmployeeDao {

	DeptService ds = new DeptServiceImpl();
	JobService js = new JobServiceImpl();
	/*
	 * 员工分页查询和搜索
	 */
	@Override
	public PageModel<Employee> findByPage(int pageIndex, Employee entity) {
		PageModel<Employee> pageModel = new PageModel<>();
		List<Object> obj = new ArrayList<>();
		pageModel.setPageIndex(pageIndex);
		String sql ="select count(id) from employee_inf where 1=1 ";
		String sql1 = "select * from employee_inf where 1=1 ";
		if (entity.getJob_id()!=null && !entity.getJob_id().equals("")) {
			sql += "and job_id = ? ";
			sql1 += "and job_id = ? ";
			obj.add(entity.getJob_id());
		}
		if (entity.getName()!=null && !entity.getName().equals("")) {
			sql += "and name like ? ";
			sql1 += "and name like ? ";
			obj.add("%"+entity.getName()+"%");
		}
		if (entity.getCardId()!=null && !entity.getCardId().equals("")) {
			sql += "and card_id like ? ";
			sql1 += "and card_id like ? ";
			obj.add("%"+entity.getCardId()+"%");
		}
		if (entity.getSex()!=null && !entity.getSex().equals("")) {
			sql += "and sex = ? ";
			sql1 += "and sex = ? ";
			obj.add(entity.getSex());
		}
		if (entity.getPhone()!=null && !entity.getPhone().equals("")) {
			sql += "and phone like ? ";
			sql1 += "and phone like ? ";
			obj.add("%"+entity.getPhone()+"%");
		}
		if (entity.getDept_id()!=null && !entity.getDept_id().equals("")) {
			sql += "and dept_id = ? ";
			sql1 += "and dept_id = ? ";
			obj.add(entity.getDept_id());
		}
		pageModel.setTotalRecordSum(getCount(sql, obj.toArray()));
		if (pageModel.getTotalPageSum() < pageIndex) {
			pageIndex = 1;
		}
		sql1 += "limit ?,?";
		obj.add((pageIndex-1)*PageModel.getPagesize());
		obj.add(PageModel.getPagesize());
		pageModel.setList(query(sql1, obj.toArray()));
		return pageModel;
	}
	/*
	 * 查找id
	 */
	@Override
	public Employee findById(Integer id) {
		List<Employee> list = query("select * from employee_inf where id = ?",id);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 员工插入
	 */
	@Override
	public boolean save(Employee entity) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		return update("insert into employee_inf values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,?)",
				entity.getName(),entity.getCardId(),entity.getAddress(),entity.getPostCode(),entity.getTel(),entity.getPhone(),entity.getQqNum()
				,entity.getEmail(),entity.getSex(),entity.getParty(),entity.getBirthday(),entity.getRace(),entity.getEducation(),entity.getSpeciality(),
				entity.getHobby(),entity.getRemark(),date,entity.getDept_id(),entity.getJob_id());
	}
	/*
	 * 员工修改
	 */
	@Override
	public boolean update(Employee entity) {
		return update("update employee_inf set name=?,card_id=?,sex=?,job_id=?,education=?,email=?,phone=?,tel=?,party=?,qq_num=?,address=?,post_code=?,birthday=?,race=?,speciality=?,hobby=?,remark=?,dept_id=? where id=?", 
				entity.getName(),entity.getCardId(),entity.getSex(),entity.getJob_id(),entity.getEducation(),entity.getEmail(),entity.getPhone(),entity.getTel(),
				entity.getParty(),entity.getQqNum(),entity.getAddress(),entity.getPostCode(),entity.getBirthday(),entity.getRace(),entity.getSpeciality(),
				entity.getHobby(),entity.getRemark(),entity.getDept_id(),entity.getId());
	}
	/*
	 * 员工删除
	 */
	@Override
	public boolean del(String id) {
		return update("delete from employee_inf where id = ?", id);
	}

	@Override
	public List<Employee> findAll() {
		return null;
	}
	/*
	 * //查询身份证号
	 */
	@Override
	public Employee findCardId(String cardId) {
		List<Employee> list = query("select * from employee_inf where card_id = ?" , cardId);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 查询手机号码
	 */
	@Override
	public Employee findPhone(String phone) {
		List<Employee> list = query("select * from employee_inf where phone = ?" , phone);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 查询电话号码
	 */
	@Override
	public Employee findTel(String tel) {
		List<Employee> list = query("select * from employee_inf where tel = ?" , tel);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 查询qq号码
	 */
	@Override
	public Employee findqqNum(String qqNum) {
		List<Employee> list = query("select * from employee_inf where qq_num = ?" , qqNum);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public Employee getEntity(ResultSet rs) throws Exception {
		Employee emp = new Employee();
		emp.setId(rs.getInt(1));
		emp.setName(rs.getString(2));
		emp.setCardId(rs.getString(3));
		emp.setAddress(rs.getString(4));
		emp.setPostCode(rs.getString(5));
		emp.setTel(rs.getString(6));
		emp.setPhone(rs.getString(7));
		emp.setQqNum(rs.getString(8));
		emp.setEmail(rs.getString(9));
		emp.setSex(rs.getInt(10));
		emp.setParty(rs.getString(11));
		emp.setBirthday(rs.getDate(12));
		emp.setRace(rs.getString(13));
		emp.setEducation(rs.getString(14));
		emp.setSpeciality(rs.getString(15));
		emp.setHobby(rs.getString(16));
		emp.setRemark(rs.getString(17));
		emp.setCreateDate(rs.getDate(18));
		emp.setDept(ds.findById(rs.getInt(20)));
		emp.setJob(js.findById(rs.getInt(21)));
		return emp;
	}
}
