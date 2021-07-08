package com.gec.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.bean.User;

@WebFilter(value= {"/viewUser.action","/useradd.action","/userdel.action","/deptadd.action","/jobadd.action","/empadd.action","/viewDept.action","/deptdel.action","/empedit.action","/empdel.action","/viewJob.action","/jobdel.action"})
public class UpdateFilter implements Filter {

	public void destroy() {

	}
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user_session");
		if(!user.getUsername().equals("超级管理员")) {
			String uri=request.getRequestURI();
			uri=uri.substring(uri.lastIndexOf("/")+1);
			if(uri.equals("viewUser.action")) {   //用户修改
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("userlist.action").forward(request, response);
			}
			if(uri.equals("useradd.action")) {  //用户添加
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("userlist.action").forward(request, response);
			}
			if(uri.equals("userdel.action")) {  //用户删除
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("userlist.action").forward(request, response);
			}
			if(uri.equals("deptadd.action")) {  //部门添加
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("deptlist.action").forward(request, response);
			}
			if(uri.equals("viewDept.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("deptlist.action").forward(request, response);
			}
			if(uri.equals("deptdel.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("deptlist.action").forward(request, response);
			}
			if(uri.equals("jobadd.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("joblist.action").forward(request, response);
			}
			if(uri.equals("viewJob.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("joblist.action").forward(request, response);
			}
			if(uri.equals("jobdel.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("joblist.action").forward(request, response);
			}
			if(uri.equals("empadd.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("emplist.action").forward(request, response);
			}
			if(uri.equals("empedit.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("emplist.action").forward(request, response);
			}
			if(uri.equals("empdel.action")) {
				request.setAttribute("message", "您不是管理员，没有该权限");
				request.getRequestDispatcher("emplist.action").forward(request, response);
			}
			
//			if(uri.equals("noticeadd.action")) {
//				request.setAttribute("message", "您不是管理员，没有该权限");
//				request.getRequestDispatcher("noticelist.action").forward(request, response);
//			}
//			if(uri.equals("viewNotice.action")) {
//				request.setAttribute("message", "您不是管理员，没有该权限");
//				request.getRequestDispatcher("noticelist.action").forward(request, response);
//			}
//			if(uri.equals("noticedel.action")) {
//				request.setAttribute("message", "您不是管理员，没有该权限");
//				request.getRequestDispatcher("noticelist.action").forward(request, response);
//			}
//			
//			if(uri.equals("typeadd.action")) {
//				request.setAttribute("message", "您不是管理员，没有该权限");
//				request.getRequestDispatcher("typelist.action").forward(request, response);
//			}
//			if(uri.equals("viewType.action")) {
//				request.setAttribute("message", "您不是管理员，没有该权限");
//				request.getRequestDispatcher("typelist.action").forward(request, response);
//			}
//			if(uri.equals("typedel.action")) {
//				request.setAttribute("message", "您不是管理员，没有该权限");
//				request.getRequestDispatcher("typelist.action").forward(request, response);
//			}
		}
		else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
