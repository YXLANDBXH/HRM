package com.gec.bean;

import java.util.Date;

public class Notice {
	private Integer id;
	private String name;
	private User user;
	private Integer user_id;
	private Date createDate;
	private Date modifyDate;
	private Types types;
	private Integer type_id;
	private String content;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}


	public Integer getType_id() {
		return type_id;
	}


	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}


	public Notice(String name) {
		this.name = name;
	}


	public Notice() {
		super();
	}


	public Types getType() {
		return types;
	}


	public void setType(Types types) {
		this.types = types;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Notice(String name, Types types) {
		super();
		this.name = name;
		this.types = types;
	}


	@Override
	public String toString() {
		return "Notice [id=" + id + ", name=" + name + ", user=" + user + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", type=" + types + ", content=" + content + "]";
	}


}
