package com.xqt.enums;



/**
 * all service error code config
 * @author andy
 */
public enum EnumPcsResultCode {
    /**
     * sys enum config
     */
    SUCCESS(0, "成功"),
    FAIL(-1,"失败"),

    LOGIN_ALREADY(1000,"用户已登录"),
    LOGIN_NO(1001,"用户未登录"),
    LOGIN_FAIL(1002,"登录失败"),

    PARAM_NO_PRIVILEGE(40000, "未授权"),
    PARAM_PUBLIC_INVALID(40001, "公共参数无效"),
    PARAM_INVALID(40002, "必填参数无效"),
    PARAM_INVALID_EMPTY(40003, "参数不能为空"),
    PARAM_MISMATCH(40004, "参数类型不匹配"),
    PARAM_JSON_CONVERT_INVALID(40005, "JSON格式不正确"),

    ERROR_SERVER(50000, "服务器异常"),
    ERROR_OPERATE(50001, "操作失败");

    private Integer code;

    private String desc;

    EnumPcsResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @param code
     * @return enum pcs
     */
    public static EnumPcsResultCode valueOf(Integer code) {
        for (EnumPcsResultCode returnCode : values()) {
            if (code.equals(returnCode.code)) {
                return returnCode;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

    public static boolean isBusinessError(EnumPcsResultCode error) {
        return error.getCode() < 50000;
    }
}
