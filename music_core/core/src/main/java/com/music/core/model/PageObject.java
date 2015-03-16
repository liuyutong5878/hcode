package com.music.core.model;

import java.util.List;

public class PageObject<T> {

	private Integer pageSize;
	
	private Integer pageNow;
	
	private Integer totalCount;
	
	private List<T> list;
	
	private Integer pageCount;

	
	public PageObject() {

	}
	
	public PageObject(Integer total, Integer pageNow) {
		this.pageSize = 10;
		this.pageCount = total%pageSize == 0 ? total/pageSize : total/pageSize + 1;
		this.totalCount = total;
		this.pageNow = pageNow;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
