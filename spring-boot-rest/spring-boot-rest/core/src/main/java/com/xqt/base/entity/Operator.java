package com.xqt.base.entity;



import com.xqt.entity.sys.SysMember;

import java.io.Serializable;

/**
 * session用户信息
 *
 * @author 梁华
 *
 */
public class Operator implements Serializable {

	/** */
	private static final long serialVersionUID = -5789922992070515459L;

	/** <code>session Id</code> */
	private String sessionId;

	/** <code>登录时间</code> */
	private SysMember member;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public SysMember getMember() {
		return member;
	}

	public void setMember(SysMember member) {
		this.member = member;
	}
}
