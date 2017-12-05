package com.xqt.base.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 * @param <T>
 * @author  andy
 */
public class PageInfo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 总行数
 	 */

	private int rowCount = 0;
	/**
	 * 总页数
	 */
	private int pageCount = 0;
	/**
	 * 获取页面显示数据
	 */
	private List<T> data;

	public PageInfo(){
		
	}
	
	public PageInfo(List<T> data, int pageSize, int rowCount, int pageId) {
		this.data = data;
		this.rowCount = rowCount;
		this.setPageCount(pageSize);
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 设置总页数
	 */
	public void setPageCount(int pageSize) {
		this.pageCount = this.rowCount / pageSize + (this.rowCount % pageSize == 0 ? 0 : 1);
		if (this.pageCount == 0) {
			if (this.rowCount == 0) {
				this.pageCount = 0;
			} else {
				this.pageCount = 1;
			}
		}
	}


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}



	
	
	
}
