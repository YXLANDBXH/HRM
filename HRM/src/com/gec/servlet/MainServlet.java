package com.gec.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(value = { "/main.action", "/top.action", "/left.action", "/right.action", "/logout.action" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到url请求路径
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		if(uri.equals("main.action")){
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(uri.equals("top.action")){
			request.getRequestDispatcher("/WEB-INF/jsp/top.jsp").forward(request, response);
		}else if(uri.equals("left.action")){
			request.getRequestDispatcher("/WEB-INF/jsp/left.jsp").forward(request, response);
		}else if(uri.equals("right.action")){
			request.getRequestDispatcher("/WEB-INF/jsp/right.jsp").forward(request, response);
		}else if (uri.equals("logout.action")) {
			HttpSession session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
		}
	}

}
