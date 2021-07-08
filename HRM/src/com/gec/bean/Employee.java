package com.gec.bean;

import java.util.Date;

public class Employee {
	private Integer id;			// id
	private Dept dept;			// 部门
	private Integer dept_id;  
	private Integer job_id;
	private Job job;			// 职位
	private String name;		// 名称
	private String cardId;		// 身份证
	private String address;		// 地址
	private String postCode;	// 邮政编码
	private String tel;			// 电话
	private String phone;		// 手机
	private String qqNum;		// qq
	private String email;		// 邮箱
	private Integer sex;		// 性别
	private String party;		// 政治面貌
	
	private Date birthday;	//生日
	private String race;				// 名族
	private String education;			// 学历
	private String speciality;			// 专业
	private String hobby;				// 爱好
	private String remark;				// 备注
	private Date createDate;	// 建档日期
	// 无参数构造器
	public Employee() {
		super();
	}
	public Employee(Integer id, Dept dept, Integer dept_id, Integer job_id, Job job, String name, String cardId,
			String address, String postCode, String tel, String phone, String qqNum, String email, Integer sex,
			String party, Date birthday, String race, String education, String speciality, String hobby, String remark,
			Date createDate) {
		this.id = id;
		this.dept = dept;
		this.dept_id = dept_id;
		this.job_id = job_id;
		this.job = job;
		this.name = name;
		this.cardId = cardId;
		this.address = address;
		this.postCode = postCode;
		this.tel = tel;
		this.phone = phone;
		this.qqNum = qqNum;
		this.email = email;
		this.sex = sex;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.createDate = createDate;
	}

	// setter和getter方法
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setCardId(String cardId){
		this.cardId = cardId;
	}
	public String getCardId(){
		return this.cardId;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setPostCode(String postCode){
		this.postCode = postCode;
	}
	public String getPostCode(){
		return this.postCode;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getTel(){
		return this.tel;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setQqNum(String qqNum){
		this.qqNum = qqNum;
	}
	public String getQqNum(){
		return this.qqNum;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setSex(Integer sex){
		this.sex = sex;
	}
	public Integer getSex(){
		return this.sex;
	}
	public void setParty(String party){
		this.party = party;
	}
	public String getParty(){
		return this.party;
	}
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	public java.util.Date getBirthday(){
		return this.birthday;
	}
	public void setRace(String race){
		this.race = race;
	}
	public String getRace(){
		return this.race;
	}
	public void setEducation(String education){
		this.education = education;
	}
	public String getEducation(){
		return this.education;
	}
	public void setSpeciality(String speciality){
		this.speciality = speciality;
	}
	public String getSpeciality(){
		return this.speciality;
	}
	public void setHobby(String hobby){
		this.hobby = hobby;
	}
	public String getHobby(){
		return this.hobby;
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
	@Override
	public String toString() {
		return "Employee [id=" + id + ", dept=" + dept + ", job=" + job
				+ ", name=" + name + ", cardId=" + cardId + ", address="
				+ address + ", postCode=" + postCode + ", tel=" + tel
				+ ", phone=" + phone + ", qqNum=" + qqNum + ", email=" + email
				+ ", sex=" + sex + ", party=" + party + ", birthday="
				+ birthday + ", race=" + race + ", education=" + education
				+ ", speciality=" + speciality + 
				", hobby=" + hobby + ", remark=" + remark + ", createDate="
				+ createDate + "]";
	}

}
