package com.xqt.entity.demo;
 
import java.io.Serializable;

public class InformationDemo implements Serializable {

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

	public InformationDemo() {
	}
	
	public InformationDemo(Long id) {
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