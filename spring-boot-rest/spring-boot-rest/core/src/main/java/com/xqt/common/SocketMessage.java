package com.xqt.common;

import java.io.Serializable;

import com.xqt.enums.CommonEnum;

/**
 * 消息推送
 * @author Andy
 *
 */
public class SocketMessage implements Serializable {

	private static final long serialVersionUID = 2204801780005198088L;

	private String code;
	
	private String message;
	
	private Object data;
	
	

	
	public SocketMessage(String message) {
		this.code = CommonEnum.socketCode.NORMAL.getValue();
		this.message = message;
	}
	
	public SocketMessage(String code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	
	public SocketMessage(Object data) {
		this.code = CommonEnum.socketCode.NORMAL.getValue();
		this.data = data;
	}

	public SocketMessage(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
