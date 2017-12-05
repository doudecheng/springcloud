package com.xqt.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户VO
 *
 * @author andy
 * @create 2017-11-20 16:51
 **/
public class SysmemberVo implements Serializable {
    private static final long serialVersionUID = -8161137997241624100L;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String mem_name;

    /**
     * QQ号
     */
    private String mem_qq;

    /**
     * 手机号
     */
    private String mem_mobile;

    /**
     * 邮箱
     */
    private String mem_email;

    /**
     * 真实姓名
     */
    private String mem_true_name;

    /**
     * 创建时间
     */
    private java.util.Date create_date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getMem_qq() {
        return mem_qq;
    }

    public void setMem_qq(String mem_qq) {
        this.mem_qq = mem_qq;
    }

    public String getMem_mobile() {
        return mem_mobile;
    }

    public void setMem_mobile(String mem_mobile) {
        this.mem_mobile = mem_mobile;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_true_name() {
        return mem_true_name;
    }

    public void setMem_true_name(String mem_true_name) {
        this.mem_true_name = mem_true_name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
