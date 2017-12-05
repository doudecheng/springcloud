package com.xqt.entity.sys;


import java.io.Serializable;

/**
 * 系统管理用户
 */
public class SysMember implements Serializable {

	private static final long serialVersionUID = -1L;


	/**
	 * id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String mem_name;

	/**
	 * 登录密码
	 */
	private String mem_pwd;

	/**
	 * QQ号
	 */
	private String mem_qq;

	/**
	 * 手机号
	 */
	private String mem_mobile;

	/**
	 * 用户头像URL
	 */
	private String mem_headimg_url;

	/**
	 * 邮箱
	 */
	private String mem_email;

	/**
	 * 真实姓名
	 */
	private String mem_true_name;

	/**
	 * 用户类型（01：平台用户、02：业务人员）
	 */
	private String mem_type;

	/**
	 * 审核状态（1：启用、2：停用）
	 */
	private Integer mem_status;

	/**
	 * 用户首页风格
	 */
	private String mem_main_style;

	/**
	 * 上级用户
	 */
	private String mem_parent_id;

	/**
	 * 创建时间
	 */
	private java.util.Date create_date;

	/**
	 * 创建人
	 */
	private String create_by;

	/**
	 * 更新时间
	 */
	private java.util.Date update_date;

	/**
	 * 更新人
	 */
	private String update_by;

	/**
	 * 注释
	 */
	private String remarks;

	/**
	 * 删除标志
	 */
	private Boolean del_flag;

	public SysMember() {
	}
	
	public SysMember(String id) {
		this.id = id;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
	
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_name() {
		return this.mem_name;
	}
	
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	public String getMem_pwd() {
		return this.mem_pwd;
	}
	
	public void setMem_qq(String mem_qq) {
		this.mem_qq = mem_qq;
	}

	public String getMem_qq() {
		return this.mem_qq;
	}
	
	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public String getMem_mobile() {
		return this.mem_mobile;
	}
	
	public void setMem_headimg_url(String mem_headimg_url) {
		this.mem_headimg_url = mem_headimg_url;
	}

	public String getMem_headimg_url() {
		return this.mem_headimg_url;
	}
	
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_email() {
		return this.mem_email;
	}
	
	public void setMem_true_name(String mem_true_name) {
		this.mem_true_name = mem_true_name;
	}

	public String getMem_true_name() {
		return this.mem_true_name;
	}
	
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}

	public String getMem_type() {
		return this.mem_type;
	}
	
	public void setMem_status(Integer mem_status) {
		this.mem_status = mem_status;
	}

	public Integer getMem_status() {
		return this.mem_status;
	}
	
	public void setMem_main_style(String mem_main_style) {
		this.mem_main_style = mem_main_style;
	}

	public String getMem_main_style() {
		return this.mem_main_style;
	}
	
	public void setMem_parent_id(String mem_parent_id) {
		this.mem_parent_id = mem_parent_id;
	}

	public String getMem_parent_id() {
		return this.mem_parent_id;
	}
	
	public void setCreate_date(java.util.Date create_date) {
		this.create_date = create_date;
	}

	public java.util.Date getCreate_date() {
		return this.create_date;
	}
	
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getCreate_by() {
		return this.create_by;
	}
	
	public void setUpdate_date(java.util.Date update_date) {
		this.update_date = update_date;
	}

	public java.util.Date getUpdate_date() {
		return this.update_date;
	}
	
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getUpdate_by() {
		return this.update_by;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}
	
	public void setDel_flag(Boolean del_flag) {
		this.del_flag = del_flag;
	}

	public Boolean getDel_flag() {
		return this.del_flag;
	}


}