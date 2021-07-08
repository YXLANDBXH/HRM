package com.gec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.bean.User;
import com.gec.service.UserService;
import com.gec.service.impl.UserServiceImpl;
import com.gec.util.PageModel;

@WebServlet(value={"/loginForm.action","/login.action","/userlist.action","/useradd.action","/userupdate.action",
		"/userdel.action","/useraddsave.action","/viewUser.action","/useredit.action","/checkName.action"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService us = new UserServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到url请求路径
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/")+1);
		/*
		 * 用户登录
		 */
		if(uri.equals("loginForm.action")){
			//因为无法直接访问页面,都需要通过转发的形式访问页面
			request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
		}else if(uri.equals("login.action")){  //用户登录
			//获取用户名和密码
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			User user = us.login(loginname, password);
			if(user!=null){
				HttpSession session = request.getSession(true);
				session.setAttribute("user_session", user);
				//跳转到主页的Servlet类
				response.sendRedirect(request.getContextPath()+"/main.action");
			}else{
				request.setAttribute("message", "用户名或密码错误");
				request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
			}
		/*
		 * 用户分页查询
		 */
		}else if (uri.equals("checkName.action")) {
			String loginname = request.getParameter("loginname");
			String id = request.getParameter("id");
			PrintWriter pw = response.getWriter();
			User user = us.findLoginName(loginname);
			if (user != null) {
				pw.print("登陆名不能重复！");
			}else {
				if (loginname != null && !loginname.equals("")) {
					pw.print("登陆名可用！");
				}else {
					pw.print("登陆名不能为空！");
				}
			}
		}else if (uri.equals("userlist.action")) {  //分页用户查询
			int pageIndex = 1;
			User user = new User();
			String index = request.getParameter("pageIndex");
			if (index != null) 
				pageIndex = Integer.parseInt(index);
			//查询获取登录名、用户名和状态
			String loginname = request.getParameter("loginname");
			String username = request.getParameter("username");
			String status = request.getParameter("status");
			if(loginname != null && !loginname.equals(""))
				user.setLoginname(loginname);
			if(username != null && !username.equals(""))
				user.setUsername(username);
			if(status != null && !status.equals(""))
				user.setStatus(Integer.parseInt(status));
			
			PageModel<User> pageModel = us.findByPage(pageIndex, user);
			request.setAttribute("user", user);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(request, response);
		/*
		 * 用户添加	
		 */
		}else if (uri.equals("useradd.action")) {  //跳转到添加页面
			request.getRequestDispatcher("/WEB-INF/jsp/user/useradd.jsp").forward(request, response);
		}else if (uri.equals("useraddsave.action")) {  //点击添加相应用户添加
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setStatus(Integer.parseInt(request.getParameter("status")));
			user.setLoginname(request.getParameter("loginname"));
			user.setPassword(request.getParameter("password"));
			if (us.save(user)) {
				response.sendRedirect(request.getContextPath()+"/userlist.action");
			}else {
				request.setAttribute("message", "添加失败");
				request.getRequestDispatcher("/WEB-INF/jsp/user/useradd.jsp").forward(request, response);
			}
		/*
		 * 用户修改
		 */
		}else if (uri.equals("viewUser.action")) {  //获取要修改的值到修改页面
			User user = us.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/jsp/user/useredit.jsp").forward(request, response);
			
		}else if (uri.equals("useredit.action")) { //修改操作
			User user = new User();
			user.setId(Integer.parseInt(request.getParameter("id")));
			user.setLoginname(request.getParameter("loginname"));
			user.setPassword(request.getParameter("password"));
			user.setUsername(request.getParameter("username"));
			user.setStatus(Integer.parseInt(request.getParameter("status")));
			if (us.update(user)) {
				response.sendRedirect(request.getContextPath()+"/userlist.action");
			}else {
				request.setAttribute("message", "添加失败");
				request.getRequestDispatcher("/WEB-INF/jsp/user/useredit.jsp").forward(request, response);
			}
		/*
		 * 用户删除	
		 */
		}else if (uri.equals("userdel.action")) {  //删除
			if(us.del(request.getParameterValues("userIds")))
				response.sendRedirect(request.getContextPath()+"/userlist.action");
		}
	}
}
