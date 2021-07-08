package com.gec.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(value="*.jsp",dispatcherTypes={DispatcherType.FORWARD,DispatcherType.REQUEST})
public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// 将请求与响应设置为Http协议对象
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getRequestURI();  //获取请求资源路径
		if(uri.contains("/WEB-INF/jsp/loginForm.jsp")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")){
			chain.doFilter(request, response);
		}else{
			//不包含对应路径的都被拦截
			Object user = request.getSession().getAttribute("user_session");
			if(user!=null)
				chain.doFilter(request, response);
			else{
				request.setAttribute("message", "您尚未登录,请登录");
				request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
			}				
		}		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}