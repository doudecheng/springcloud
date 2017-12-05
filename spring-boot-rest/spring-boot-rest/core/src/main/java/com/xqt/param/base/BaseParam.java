package com.xqt.param.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 基础请求参数
 * @author andy
 *
 */
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 6032664052762427462L;

    /**
     * 是否需要分页
     */
    private boolean limitDirection = false;
    /**
     * 分页请求参数
     * 当前请求页码
     * default 1
     */
    private int pageId = 1;

    /**
     * 分页请求参数
     * 请求页大小
     * default 10
     */
    private int pageSize = 10;

    /**
     * 分页请求参数
     * 分页起始记录
     * (this.pageId - 1) * this.pageSize;
     */
    private int pageOffset = 0;

    /**
     * 排序字段
     */
    private String orderField;

    /**
     * 扩展map
     */
    private Map<String, Object> map = new HashMap<String,Object>();

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }


    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageOffset() {
        if (this.pageId != 0) {
            pageOffset = (this.pageId - 1) * this.pageSize;
        }
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isLimitDirection() {
        return limitDirection;
    }

    public void setLimitDirection(boolean limitDirection) {
        this.limitDirection = limitDirection;
    }

    
}
