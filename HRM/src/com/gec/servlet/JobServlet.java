package com.gec.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.bean.Job;
import com.gec.service.JobService;
import com.gec.service.impl.JobServiceImpl;
import com.gec.util.PageModel;

@WebServlet(value = {"/joblist.action","/jobadd.action","/addOrUpdate.action","/jobdel.action","/viewJob.action"})
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JobService js = new JobServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		/*
		 * 职员分页查询
		 */
		if (uri.equals("joblist.action")) {   //职位查询
			int pageIndex = 1;
			Job job = new Job();
			String index = request.getParameter("pageIndex");
			if (index != null) 
				pageIndex = Integer.parseInt(index);
			//查询获取登录名、用户名和状态
			String deptname = request.getParameter("name");
			if (deptname != null && !deptname.equals("")) {
				job.setName(deptname);
			}
			PageModel<Job> pageModel = js.findByPage(pageIndex, job);
			request.setAttribute("job", job);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/WEB-INF/jsp/job/joblist.jsp").forward(request, response);
		
		/*
		 * 职员添加和修改
		 */
		}else if (uri.equals("jobadd.action")) { //职位添加
			request.setAttribute("val", "add");
			request.getRequestDispatcher("/WEB-INF/jsp/job/jobedit.jsp").forward(request, response);
		}else if (uri.equals("viewJob.action")) {  //职位修改
			Job job = js.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("job", job);
			request.getRequestDispatcher("/WEB-INF/jsp/job/jobedit.jsp").forward(request, response);
		}else if (uri.equals("addOrUpdate.action")) {
			Job job = new Job();
			String id = request.getParameter("id");
			job.setName(request.getParameter("name"));
			job.setRemark(request.getParameter("remark"));
			if (id!=null&&!id.equals("")) { 
				job.setId(Integer.parseInt(id));
				if(js.update(job)) {
					response.sendRedirect(request.getContextPath()+"/joblist.action");
				}else {
					request.setAttribute("message", "修改失败");
					request.getRequestDispatcher("/WEB-INF/jsp/job/jobedit.jsp").forward(request, response);
				}
			}else {
				if (js.save(job)) {
					response.sendRedirect(request.getContextPath()+"/joblist.action");
				}else {
					request.setAttribute("message", "添加失败");
					request.getRequestDispatcher("/WEB-INF/jsp/job/jobedit.jsp").forward(request, response);
				}
			}
		/*
		 * 职位删除
		 */
		}else if (uri.equals("jobdel.action")) { //职位删除
			if(js.del(request.getParameterValues("jobIds")))
				response.sendRedirect(request.getContextPath()+"/joblist.action");
		}
	}
}
