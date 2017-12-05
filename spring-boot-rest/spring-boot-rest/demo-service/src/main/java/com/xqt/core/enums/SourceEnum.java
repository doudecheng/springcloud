package com.xqt.core.enums;

/**
 * 数据源Enum
 * 
 * @author 梁华
 */
public enum SourceEnum {
	TYPE_MASTER("master", "主库"), TYPE_SLAVE("slave", "从库");
	// 枚举值
	private String value;
	// 描述
	private String desc;

	private SourceEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
