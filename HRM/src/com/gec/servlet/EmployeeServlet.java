package com.gec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.bean.Dept;
import com.gec.bean.Employee;
import com.gec.bean.Job;
import com.gec.service.DeptService;
import com.gec.service.EmployeeService;
import com.gec.service.JobService;
import com.gec.service.TypesService;
import com.gec.service.impl.DeptServiceImpl;
import com.gec.service.impl.EmployeeServiceImpl;
import com.gec.service.impl.JobServiceImpl;
import com.gec.service.impl.TypesServiceImpl;
import com.gec.util.PageModel;

@WebServlet(value = {"/emplist.action","/empadd.action","/emsaveOrUpdate.action","/empedit.action",
		"/empdel.action","/checkCardId.action","/checkPhone.action","/checkTel.action","/checkqqNum.action"})
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeService es = new EmployeeServiceImpl();
	DeptService ds = new DeptServiceImpl();
	JobService js = new JobServiceImpl();
	TypesService ts = new TypesServiceImpl();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		List<Dept> deptList = ds.findAll();
		List<Job> jobList = js.findALL();
		/*
		 * 员工信息分页查询
		 */
		if (uri.equals("emplist.action")) {
			int pageIndex = 1;
			Employee emp = new Employee();

			String index = request.getParameter("pageIndex");
			if (index != null) 
				pageIndex = Integer.parseInt(index);
			
			//模糊查询
			String job_id = request.getParameter("job_id");  //职位编号
			if (job_id!=null && !job_id.equals("")) {
				emp.setJob_id(Integer.parseInt(job_id));
			}
			
			String dept_id = request.getParameter("dept_id");   //部门编号
			if (dept_id!=null && !dept_id.equals("")) {
				emp.setDept_id(Integer.parseInt(dept_id));
			}
			String name = request.getParameter("name");
			if (name!=null && !name.equals("")) {
				emp.setName(name);
			}
			String cardId = request.getParameter("cardId");  //身份证号
			if (cardId!=null && !cardId.equals("")) {
				emp.setCardId(cardId);
			}
			String sex = request.getParameter("sex");
			if (sex!=null && !sex.equals("")) {
				emp.setSex(Integer.parseInt(sex));
			}
			String phone = request.getParameter("phone");
			if (phone!=null && !phone.equals("")) {
				emp.setPhone(phone);
			}
			
			PageModel<Employee> pageModel = es.findByPage(pageIndex, emp);
			request.setAttribute("jobList", jobList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("emp", emp);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/WEB-INF/jsp/employee/employeelist.jsp").forward(request, response);
		/*
		 * 验证省份证号码
		 */
		}else if (uri.equals("checkCardId.action")) {
			String cardId = request.getParameter("cardId");
			PrintWriter pw = response.getWriter();
			Employee emp = es.findCardId(cardId);
			if (emp != null) {
				pw.print("身份证不能重复！");
			}else {
				if (cardId != null && !cardId.equals("")) {
					pw.print("身份证可用！");
				}else {
					pw.print("身份证不能为空！");
				}
			}
		/*
		 * 验证手机
		 */
		}else if (uri.equals("checkPhone.action")) {
			String phone = request.getParameter("phone");
			PrintWriter pw = response.getWriter();
			Employee emp = es.findPhone(phone);
			if (emp != null) {
				pw.print("电话号码不能重复！");
			}else {
				if (phone != null && !phone.equals("")) {
					pw.print("电话号码可用！");
				}else {
					pw.print("电话号码不能为空！");
				}
			}
		/*
		 * 验证电话
		 */
		}else if (uri.equals("checkTel.action")) {
			String tel = request.getParameter("tel");
			PrintWriter pw = response.getWriter();
			Employee emp = es.findTel(tel);
			if (emp != null) {
				pw.print("电话不能重复！");
			}else {
				if (tel != null && !tel.equals("")) {
					pw.print("电话可用！");
				}else {
					pw.print("电话不能为空！");
				}
			}
			
		/*
		 * 验证qq号码
		 */
		}else if (uri.equals("checkqqNum.action")) {
			String qqNum = request.getParameter("qqNum");
			PrintWriter pw = response.getWriter();
			Employee emp = es.findqqNum(qqNum);
			if (emp != null) {
				pw.print("qq号不能重复！");
			}else {
				if (qqNum != null && !qqNum.equals("")) {
					pw.print("qq号可用！");
				}else {
					pw.print("qq号不能为空！");
				}
			}
		/*
		 * 员工添加和修改
		 */	
		}else if (uri.equals("empadd.action")) {
			request.setAttribute("val", "add");
			request.setAttribute("jobList", jobList);
			request.setAttribute("deptList", deptList);
			request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeedit.jsp").forward(request, response);
		}else if (uri.equals("empedit.action")) {   //获取要修改的数据到页面
			Employee emp = es.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("employee", emp);
			request.setAttribute("jobList", jobList);
			request.setAttribute("deptList", deptList);
			request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeedit.jsp").forward(request, response);
		}else if (uri.equals("emsaveOrUpdate.action")) {
			Employee emp = new Employee();
			//获取修改信息
			String id = request.getParameter("id");
			emp.setName(request.getParameter("name"));
			emp.setCardId(request.getParameter("cardId"));
			String sex = request.getParameter("sex");
			if (sex!=null && !sex.equals("")) {
				emp.setSex(Integer.parseInt(sex));
			}
			emp.setJob_id(Integer.parseInt(request.getParameter("job_id")));
			emp.setEducation(request.getParameter("education"));
			emp.setPhone(request.getParameter("phone"));
			emp.setTel(request.getParameter("tel"));
			emp.setEmail(request.getParameter("email"));
			emp.setParty(request.getParameter("party"));
			emp.setQqNum(request.getParameter("qqNum"));
			emp.setAddress(request.getParameter("address"));
			emp.setPostCode(request.getParameter("postCode"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			try {
				emp.setBirthday(sdf.parse(request.getParameter("birthday")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			emp.setRace(request.getParameter("race"));
			emp.setSpeciality(request.getParameter("speciality"));
			emp.setHobby(request.getParameter("hobby"));
			emp.setRemark(request.getParameter("remark"));
			emp.setDept_id(Integer.parseInt(request.getParameter("dept_id")));
			if (id!=null&&!id.equals("")) {   //修改
				emp.setId(Integer.parseInt(id));
				if (es.update(emp)) {
					response.sendRedirect(request.getContextPath()+"/emplist.action");
				}else {
					request.setAttribute("message", "修改失败");
					request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeedit.jsp").forward(request, response);
				}
			}else { 
				if (es.save(emp)) {   //添加
					response.sendRedirect(request.getContextPath()+"/emplist.action");
				}else {
					request.setAttribute("message", "添加失败");
					request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeedit.jsp").forward(request, response);
				}
			}
		/*
		 * 员工删除
		 */
		}else if (uri.equals("empdel.action")) {
			if(es.del(request.getParameterValues("employeeIds")))
				response.sendRedirect(request.getContextPath()+"/emplist.action");
		}
	}
}
