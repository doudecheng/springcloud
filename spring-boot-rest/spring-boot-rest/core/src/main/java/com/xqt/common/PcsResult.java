package com.xqt.common;


import com.xqt.enums.EnumPcsResultCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This is common used for API results.
 * @author andy
 */
public class PcsResult implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8315211647714974450L;
	/**
     * Result code.
     */
    private String code;
    /**
     * Result message, mainly used for error message.
     */
    private String message;
    /**
     * Result data which contains resource entity.
     */
    private Object data;
    /**
     * Contains expand data of resource entity.
     */
    private Object token;
    /**
     * 
     */
    public PcsResult() {
        this(EnumPcsResultCode.SUCCESS);
    }

    /**
     * 
     * @param o
     */
    public PcsResult(Object o) {
        this(EnumPcsResultCode.SUCCESS);
        this.data = o;
    }
    
    /**
     * 
     * @param code
     */
    public PcsResult(EnumPcsResultCode code) {
        this(code.getCode(), code.getDesc());
    }
    
    /**
     * 
     * @param code
     * @param message
     */
    public PcsResult(Integer code, String message) {
        this.code = String.valueOf(code);
        this.message = message;
    }

    public PcsResult(EnumPcsResultCode code, String message) {
        this(code);
        this.message = message;
    }

    public PcsResult(EnumPcsResultCode code, String message, Object data) {
        this(code);
        this.message = message;
        this.data = data;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     * @return PcsResult
     */
    public PcsResult setCode(Integer code) {
        this.code = String.valueOf(code);
        return this;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     * @return PcsResult
     */
    public PcsResult setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Set result data, with map contains key and value. {data: {key : value}}
     * 
     * @param data
     *            the data to set
     * @return PcsResult
     */
    public PcsResult setDataMap(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    /**
     * Set result data, simply return data value. {data: value} <br>
     * Note: if want to result include entity name, call
     * {@code setDataEntity(Object)}
     * 
     * @param data
     *            the value to set
     * @return PcsResult
     */
    public PcsResult setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * Set result data, by special key and value. {data: {key : value}}
     * 
     * @param key
     *            the key of value.
     * @param value
     *            the value to set.
     * @return PcsResult
     */
    public PcsResult setData(String key, Object value) {
        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put(key, value);
        this.data = dataMap;
        return this;
    }

    /**
     * Set result data, the value of key will be generated automatically base on
     * entity class simple name.{data: {objectClassName : value}} <br>
     * Notice, if the value is not our entity type, like String, Long, should
     * call {@code setData(String, Object)} or {@code setData(Object)}
     * 
     * @param value
     *            Object to set to result data.
     * @return PcsResult
     */
    public PcsResult setDataEntity(Object value) {
        this.data = value;
        return this;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }
}
