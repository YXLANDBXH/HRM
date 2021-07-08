package com.gec.bean;

import java.util.Date;

public class Types {
	private Integer id;
	private String name;
	private User user;
	private Integer user_id;
	private Date createDate;
	private Date modifyDate;
	
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


	public Types(String name) {
		this.name = name;
	}


	public Types() {
		super();
	}


	public Types(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Notice [id=" + id + ", name=" + name + ", user=" + user + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + "]";
	}
}
