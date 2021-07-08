package com.gec.bean;

public class Document {
	private Integer id;		
	// 编号
	private String title;			// 标题
	private String fileName;		// 文件名
	private String filetype;        //文件类型
	private String fileBytes;       //文件内容
	private String remark;			// 描述
	private java.util.Date createDate;	// 上传时间
	private Integer user_id;
	private User user;				// 上传人
	// 无参数构造器
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	// setter和getter方法

	public void setTitle(String title){
		this.title = title;
	}
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle(){
		return this.title;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(String fileBytes) {
		this.fileBytes = fileBytes;
	}

	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
}
