package com.lin.security.page;

import java.util.List;

import org.springframework.util.Assert;

/**
 * 
 * desc: 分页
 * 
 * @author xuelin
 * @date Dec 21, 2015
 */
public final class PageInfo<T> {
	
	public static final int DEFAULT_PAGE = 1;
	public static final short DEFAULT_SIZE = 10;
	
	private int pageSize;
	public int currPage;
	private long total;
	private Integer pageCount;
	private List<T> list;
	
	public PageInfo() {
		this(0l);
	}

	public PageInfo(long total) {
		this(DEFAULT_SIZE, total);
	}
	
	public PageInfo(int total) {
		this(DEFAULT_SIZE, total);
	}

	public PageInfo(int pageSize, long total) {
		this(DEFAULT_PAGE, pageSize, total);
	}
	
	public PageInfo(int pageSize, int total) {
		this(DEFAULT_PAGE, pageSize, total);
	}

	public PageInfo(int currPage, int pageSize, long total) {
		super();
		
		Assert.state(currPage > 0);
		Assert.state(pageSize > 0);
		
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.total = total;
	}

	public long getCurrPage() {
		return currPage;
	}
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getOffset(){
		return 1l * (this.currPage - 1) * this.pageSize;
	}

	public int getPageCount() {
		if(null == pageCount){
			int mod = (int) (this.total % this.pageSize);
			pageCount = (int) (this.total / this.pageSize);
			if(mod > 0){
				pageCount += pageCount + 1;
			}
		}
		return pageCount;
	}
	
}
