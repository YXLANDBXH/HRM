package com.gec.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.bean.Dept;
import com.gec.service.DeptService;
import com.gec.service.impl.DeptServiceImpl;
import com.gec.util.PageModel;

@WebServlet(value = {"/deptlist.action","/deptadd.action","/saveOrUpdate.action","/viewDept.action","/deptdel.action"})
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptService ds = new DeptServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到url请求路径
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		/*
		 * 部门分页查询
		 */
		if (uri.equals("deptlist.action")) {  //查询部门信息
			int pageIndex = 1;
			Dept dept = new Dept();
			String index = request.getParameter("pageIndex");
			if (index != null) 
				pageIndex = Integer.parseInt(index);
			//查询获取登录名、用户名和状态
			String deptname = request.getParameter("name");
			if (deptname != null && !deptname.equals("")) {
				dept.setName(deptname);
			}
			PageModel<Dept> pageModel = ds.findByPage(pageIndex, dept);
			request.setAttribute("dept", dept);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/WEB-INF/jsp/dept/deptlist.jsp").forward(request, response);
		/*
		 * 部门添加和修改
		 */
		}else if (uri.equals("deptadd.action")) { //部门添加
			request.setAttribute("val", "add");
			request.getRequestDispatcher("/WEB-INF/jsp/dept/deptedit.jsp").forward(request, response);
		}else if (uri.equals("viewDept.action")) {  //获取要修改的数据到页面
			Dept dept = ds.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("dept", dept);
			request.getRequestDispatcher("/WEB-INF/jsp/dept/deptedit.jsp").forward(request, response);
		}else if (uri.equals("saveOrUpdate.action")) {  //修改操作
			Dept dept = new Dept();
			String id = request.getParameter("id");
			dept.setName(request.getParameter("name"));
			dept.setRemark(request.getParameter("remark"));
			if (id!=null&&!id.equals("")) {   //修改
				dept.setId(Integer.parseInt(id));
				if (ds.update(dept)) {
					response.sendRedirect(request.getContextPath()+"/deptlist.action");
				}else {
					request.setAttribute("message", "修改失败");
					request.getRequestDispatcher("/WEB-INF/jsp/dept/deptedit.jsp").forward(request, response);
				}
			}else { 
				if (ds.save(dept)) {   //添加
					response.sendRedirect(request.getContextPath()+"/deptlist.action");
				}else {
					request.setAttribute("message", "添加失败");
					request.getRequestDispatcher("/WEB-INF/jsp/dept/deptedit.jsp").forward(request, response);
				}
			}
			
		/*
		 * 部门删除	
		 */
		}else if (uri.equals("deptdel.action")) {  //删除
			if(ds.del(request.getParameterValues("deptIds")))
				response.sendRedirect(request.getContextPath()+"/deptlist.action");
		}
	}

}
