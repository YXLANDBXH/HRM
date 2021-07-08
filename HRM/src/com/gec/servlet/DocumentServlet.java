package com.gec.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gec.bean.Document;
import com.gec.bean.User;
import com.gec.service.DocumentService;
import com.gec.service.impl.DocumentServiceImpl;
import com.gec.util.PageModel;

@WebServlet(value = {"/documentlist.action","/documentdownload.action","/documentadd.action","/removeDocument","/viewDocument.action","/updateDocument",
		"/documentaddsave.action","/documentprevload.action"})
public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DocumentService ds = new DocumentServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		/*
		 * 文档查询
		 */
		if (uri.equals("documentlist.action")) {
			int pageIndex = 1;
			Document document = new Document();
			String index = request.getParameter("pageIndex");
			if (index != null) 
				pageIndex = Integer.parseInt(index);
			//查询获取登录名、用户名和状态
			String title = request.getParameter("title");
			if (title != null && !title.equals("")) {
				document.setTitle(title);
			}
			PageModel<Document> pageModel = ds.findByPage(pageIndex, document);
			request.setAttribute("document", document);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(request, response);
		/*
		 * 上传文档
		 */
		}else if (uri.equals("documentadd.action")) {
			request.getRequestDispatcher("/WEB-INF/jsp/document/documentadd.jsp").forward(request, response);
		}else if (uri.equals("documentaddsave.action")) {
			Document document = new Document();
			//获取页面传输内容是否为二进制文件
			if(ServletFileUpload.isMultipartContent(request)){
				//创建一个FileItemFactory工厂  用于解析request
				FileItemFactory factory = new DiskFileItemFactory();
				//创建生成一个ServletFileUpload组件
				ServletFileUpload upload = new ServletFileUpload(factory);
				boolean flag = false;
				try {
					@SuppressWarnings("unchecked")  //忽略警告
					//通过该组件解析request中的内容  为多个文件项FileItem
					List<FileItem> list = upload.parseRequest(request);
					if(list != null) {
						User user = (User)request.getSession().getAttribute("user_session");
						document.setUser(user);
					}
					//循环每一项内容
					if(list!=null){
						for (FileItem fi : list) {
							//判断每一项内容是否为普通表单数据
							if(fi.isFormField()){
								if("id".equals(fi.getFieldName())){
									document.setId(Integer.parseInt(fi.getString("utf-8")));
								}
								//判断获取到属性名称是否为我们需要的属性  getFieldName
								if("title".equals(fi.getFieldName())){
									document.setTitle(fi.getString("utf-8"));
									//获得对应的值
									//System.out.println("用户名为:"+fi.getString("utf-8"));
								}
								if("remark".equals(fi.getFieldName())){
									document.setRemark(fi.getString("utf-8"));
								//	System.out.println("密码为:"+fi.getString());
								}
							}else{
								//获取上传文件地址(要上传的地址)  真实路径
								String path = request.getServletContext().getRealPath("/upload");
								System.out.println("真实路径为:"+path);
								//如果该路径不存在则创建
								File file = new File(path);
								if(!file.exists())
									file.mkdirs();
								//获取到文件名,并拼接在原有路径   getName
								String fileName = fi.getName();
                                String fileLast = "";
                                document.setFileName(fileName);
                                if (fileName != "" && fileName != null) {
                                    fileLast = fileName.substring(fileName.lastIndexOf("."));
                                }

                                document.setFiletype("未知");
                                if (".jpg".equals(fileLast) || ".png".equals(fileLast)) {
                                    document.setFiletype("图片");
                                }
                                if (".mp4".equals(fileLast)) {
                                    document.setFiletype("视频");
                                }
                                if (".mp3".equals(fileLast)) {
                                    document.setFiletype("音频");
                                }
                                if (".txt".equals(fileLast)) {
                                    document.setFiletype("文本");
                                }
                                if (fileName != "" && fileName != null) {
                                    fileName = fileName.substring(0, fileName.lastIndexOf(".")) + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
                                    File newFile = new File(file, fileName);
                                    fi.write(newFile);
                                }
                                flag = true;
                                document.setFileBytes(fileName);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(flag){
//					System.out.println("表单数据提交成功,存入数据库");
					if(ds.save(document)){
						request.getRequestDispatcher("documentlist.action").forward(request, response);
					}else{
						request.getRequestDispatcher("documentadd.action").forward(request, response);
					}
				}
			}else{
				String name = request.getParameter("name");
				System.out.println(name);
			}
		/*
		 * 文档修改
		 */
		}else if (uri.equals("viewDocument.action")) {
			Document document = ds.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("document", document);
			request.getRequestDispatcher("/WEB-INF/jsp/document/showUpdateDocument.jsp").forward(request, response);
		}else if (uri.equals("updateDocument")) {
			Document document = new Document();
			//获取页面传输内容是否为二进制文件
			if(ServletFileUpload.isMultipartContent(request)){
				//创建一个FileItemFactory工厂  用于解析request
				FileItemFactory factory = new DiskFileItemFactory();
				//创建生成一个ServletFileUpload组件
				ServletFileUpload upload = new ServletFileUpload(factory);
				boolean flag = false;
				try {
					@SuppressWarnings("unchecked")  //忽略警告
					//通过该组件解析request中的内容  为多个文件项FileItem
					List<FileItem> list = upload.parseRequest(request);
					//循环每一项内容
					if(list!=null){
						for (FileItem fi : list) {
							//判断每一项内容是否为普通表单数据
							if(fi.isFormField()){
								//判断获取到属性名称是否为我们需要的属性  getFieldName
								if("id".equals(fi.getFieldName())){
									document.setId(Integer.parseInt(fi.getString("utf-8")));
								}
								if("title".equals(fi.getFieldName())){
									document.setTitle(fi.getString("utf-8"));
								}
								if("remark".equals(fi.getFieldName())){
									document.setRemark(fi.getString("utf-8"));
								}

							}else{
								String path = request.getServletContext().getRealPath("/upload");
								File file = new File(path);
								if(!file.exists())
									file.mkdirs();
								//获取到文件名,并拼接在原有路径   getName
								String name = fi.getName();
								String[] names = name.split("\\.");
								//再次存入name属性
								name = names[0]+System.currentTimeMillis()+"."+names[1];
								document.setFileName(name);  //将文件名保存到数据库
								File newFile = new File(file,name );
								//将二进制数据写入该文件
								fi.write(newFile);
//								System.out.println("上传成功!");
								flag = true;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(flag){
					if(ds.update(document)){
						response.sendRedirect(request.getContextPath()+"/documentlist.action");
					}else{
						request.setAttribute("message", "修改失败");
						request.getRequestDispatcher("/WEB-INF/jsp/document/showUpdateDocument.jsp").forward(request, response);
					}
				}
			}else{
				String name = request.getParameter("name");
				System.out.println(name);
			}

		/*
		 * 下载文档
		 */
		}else if (uri.equals("documentdownload.action")) {
			String path = request.getServletContext().getRealPath("/upload");
			String fileName = request.getParameter("id");
			File file = new File(path+File.separator+ds.findById(Integer.parseInt(fileName)).getFileBytes());
			if (!file.exists()) {
				request.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(request, response);
			}
			InputStream in = new FileInputStream(path+File.separator+ds.findById(Integer.parseInt(fileName)).getFileBytes());
			//设置响应的方式为下载方式,filename为下载名称
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			//输出对象建立
			ServletOutputStream out = response.getOutputStream();
			int len = 0;
			byte[] b = new byte[1024];
			while((len=in.read(b))!=-1){
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
		/*
		 * 上传文件预览
		 */
		}else if (uri.equals("documentprevload.action")) {
			String path = request.getServletContext().getRealPath("/upload");
			String fileName = request.getParameter("id");
			File file = new File(path+File.separator+ds.findById(Integer.parseInt(fileName)).getFileBytes());
			if (!file.exists()) {
				request.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(request, response);
			}
			InputStream in = new FileInputStream(path+File.separator+ds.findById(Integer.parseInt(fileName)).getFileBytes());
			//设置响应的方式为下载方式,filename为下载名称
//			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			//输出对象建立
			ServletOutputStream out = response.getOutputStream();
			int len = 0;
			byte[] b = new byte[1024];
			while((len=in.read(b))!=-1){
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
			
		/*
		 * 删除文档
		 */
		}else if (uri.equals("removeDocument")) {
			if(ds.del(request.getParameterValues("documentIds"))) 
				response.sendRedirect(request.getContextPath()+"/documentlist.action");
		}
	}
}

