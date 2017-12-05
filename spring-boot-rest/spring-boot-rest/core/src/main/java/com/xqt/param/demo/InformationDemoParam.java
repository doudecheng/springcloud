package com.xqt.param.demo;
 
import com.xqt.param.base.BaseParam;

import java.io.Serializable;

public class InformationDemoParam extends BaseParam implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * id
	 */
	private Long id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	public InformationDemoParam() {
	}
	
	public InformationDemoParam(Long id) {
		this.id = id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}


}