package com.wonders.bud.framework.common.page;

import java.util.List;
import java.util.Map;

/**
 * 分页vo
 * @author dy
 *
 */
public class PageVO<T> {
	
	int total;			//记录总数
	int page;		//当前页数
	int row;		//当前每页条数
	int totalPages;		//总页数
	List<T> data;			//结果列表
	Map<String, Object> ext; //额外信息
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public Map<String, Object> getExt() {
		return ext;
	}
	public void setExt(Map<String, Object> ext) {
		this.ext = ext;
	}
	
}
