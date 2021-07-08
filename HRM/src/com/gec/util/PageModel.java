package com.gec.util;

import java.util.List;

public class PageModel<T> {
	
	private Integer pageIndex; //当前页码
	public static final Integer pageSize = 4; //每页显示数量
	private Integer totalPageSum; //总页码
	private Integer totalRecordSum; //总记录数
	private List<T> list; //页面数据
	
	public Integer getPageIndex() {  //获取当前页码
		//如果输入的数小于等于1
		this.pageIndex = this.pageIndex <= 1 ? 1:this.pageIndex;
		
		//如果输入的当前页码是大于或等于总页码 
		this.pageIndex = this.pageIndex >= getTotalPageSum() ? getTotalPageSum():this.pageIndex;
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getTotalPageSum() {  //获得总页码
		// 30条记录 每页10条 30/10=3 29/10=2 后面这种情况 要加1
		this.totalPageSum = getTotalRecordSum() % getPagesize() == 0 ? getTotalRecordSum()/getPagesize():
			getTotalRecordSum()/getPagesize()+1;
		return totalPageSum;
	}
	public void setTotalPageSum(Integer totalPageSum) {
		this.totalPageSum = totalPageSum;
	} 
	public Integer getTotalRecordSum() {
		return totalRecordSum;
	}
	public void setTotalRecordSum(Integer totalRecordSum) {
		this.totalRecordSum = totalRecordSum;
	}
	public static Integer getPagesize() {
		return pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	//提供一个方法计算起始行
	public int getStartRow() {
		int start = (getPageIndex() -1 ) >= 1 ? (getPageIndex() -1) * getPagesize() : 0;
		return start;
	}
}
