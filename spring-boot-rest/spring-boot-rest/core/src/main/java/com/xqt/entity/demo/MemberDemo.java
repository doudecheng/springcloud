package com.xqt.entity.demo;
 

import java.io.Serializable;

public class MemberDemo implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * id
	 */
	private Long id;

	/**
	 * username
	 */
	private String username;

	/**
	 * password
	 */
	private String password;

	/**
	 * age
	 */
	private Byte age;

	/**
	 * sex
	 */
	private String sex;

	public MemberDemo() {
	}
	
	public MemberDemo(Long id) {
		this.id = id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setAge(Byte age) {
		this.age = age;
	}

	public Byte getAge() {
		return this.age;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return this.sex;
	}


}