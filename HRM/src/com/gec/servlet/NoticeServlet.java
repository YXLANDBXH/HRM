package com.gec.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.bean.Notice;
import com.gec.bean.Types;
import com.gec.service.NoticeService;
import com.gec.service.TypesService;
import com.gec.service.impl.NoticeServiceImpl;
import com.gec.service.impl.TypesServiceImpl;
import com.gec.util.PageModel;

@WebServlet(value = {"/noticelist.action","/noticeadd.action","/typelist.action","/typeadd.action"
		,"/viewType.action","/viewNotice.action","/noticesaveOrUpdate.action","/noticedel.action",
		"/typesaveOrUpdate.action","/typedel.action"})
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NoticeService ns = new NoticeServiceImpl();
	TypesService ts = new TypesServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
//		List<Notice> noticelist = ns.findAll();
		List<Types> typeslist = ts.findAll();
		/*
		 * 公告查询
		 */
		if (uri.equals("noticelist.action")) {
			int pageIndex = 1;
			Notice notice = new Notice();
			String index = request.getParameter("pageIndex");
			if (index != null) 
				pageIndex = Integer.parseInt(index);
		
			String name = request.getParameter("name");
			if (name != null && !name.equals("")) {
				notice.setName(name);
			}
			PageModel<Notice> pageModel = ns.findByPage(pageIndex, notice);
//			request.setAttribute("noticelist", noticelist);
			request.setAttribute("notice", notice);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/noticelist.jsp").forward(request, response);
		/*
		 * 公告添加和修改
		 */
		}else if (uri.equals("noticeadd.action")) {
			request.setAttribute("types", typeslist);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/notice_save_update.jsp").forward(request, response);
		}else if (uri.equals("viewNotice.action")) {
			Notice notice = ns.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("notice", notice);
			request.setAttribute("types", typeslist);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/notice_save_update.jsp").forward(request, response);
		}else if (uri.equals("noticesaveOrUpdate.action")) {
			Notice notice = new Notice();
			notice.setName(request.getParameter("name"));
			notice.setType_id(Integer.parseInt(request.getParameter("type_id")));
//			notice.setType(ts.findById(Integer.parseInt(request.getParameter("type_id"))));
			notice.setContent(request.getParameter("text"));
			String id = request.getParameter("id");
			if (id!=null&&!id.equals("")) {   //修改
				notice.setId(Integer.parseInt(id));
				if (ns.update(notice)) {
					response.sendRedirect(request.getContextPath()+"/noticelist.action");
				}else {
					request.setAttribute("message", "修改失败");
					request.getRequestDispatcher("/WEB-INF/jsp/notice/notice_save_update.jsp").forward(request, response);
				}
			}else {
				if (ns.save(notice)) {   //添加
					response.sendRedirect(request.getContextPath()+"/noticelist.action");
				}else {
					request.setAttribute("message", "添加失败");
					request.getRequestDispatcher("/WEB-INF/jsp/notice/notice_save_update.js").forward(request, response);
				}
			}
		/*
		 * 公告删除
		 */
		}else if (uri.equals("noticedel.action")) {
			if(ns.del(request.getParameterValues("noticeIds")))
				response.sendRedirect(request.getContextPath()+"/noticelist.action");
		/*
		 * 公告类型查询
		 */
		}else if (uri.equals("typelist.action")) {
			int pageIndex = 1;
			Types types = new Types();
			String index = request.getParameter("pageIndex");
			if(index != null)
				pageIndex = Integer.parseInt(index);
			String name = request.getParameter("name");
			if (name!=null && !name.equals("")) {
				types.setName(name);
			}
			PageModel<Types> pageModel = ts.findByPage(pageIndex, types);
			request.setAttribute("type", types);
			request.setAttribute("pageModel", pageModel);
//			request.setAttribute("typelist", typeslist);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/typelist.jsp").forward(request, response);
		/*
		 * 公告类型添加和修改
		 */
		}else if (uri.equals("typeadd.action")) {
			request.getRequestDispatcher("/WEB-INF/jsp/notice/type_save_update.jsp").forward(request, response);
		}else if (uri.equals("viewType.action")) {
			Types types = ts.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("type", types);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/type_save_update.jsp").forward(request, response);
		}else if (uri.equals("typesaveOrUpdate.action")) {
			Types types = new Types();
			String id = request.getParameter("id");
			types.setName(request.getParameter("name"));
			if (id!=null&&!id.equals("")) {  //修改
				types.setId(Integer.parseInt(id));
				if (ts.update(types)) {
					response.sendRedirect(request.getContextPath()+"/typelist.action");
				}else {
					request.setAttribute("message", "修改失败");
					request.getRequestDispatcher("/WEB-INF/jsp/notice/type_save_update.jsp").forward(request, response);
				}
			}else { 
				if (ts.save(types)) {   //添加
					response.sendRedirect(request.getContextPath()+"/typelist.action");
				}else {
					request.setAttribute("message", "添加失败");
					request.getRequestDispatcher("/WEB-INF/jsp/notice/type_save_update.jsp").forward(request, response);
				}
			}
		/*
		 * 公告类型删除
		 */
		}else if (uri.equals("typedel.action")) {
			if(ts.del(request.getParameterValues("typeIds")))
				response.sendRedirect(request.getContextPath()+"/typelist.action");
		}
	}
}
