package com.xqt.enums;

public class AppEnum {

	public enum app_type {
		TYPE_01("1", "商户端"),TYPE_02("2", "消费者端"),TYPE_03("3", "面前端");
		// 枚举值
		private String value;
		// 描述
		private String desc;
		
		private app_type(String value, String desc) {
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
	
	public enum version_type {
		TYPE_01("1", "安卓端"),TYPE_02("2", "IOS端");
		// 枚举值
		private String value;
		// 描述
		private String desc;
		
		private version_type(String value, String desc) {
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
	
	public enum app_send_status {
		STATUS_1("1", "已发版"), STATUS_2("2", "未发版");

		// 枚举值
		private String value;

		// 描述
		private String desc;

		private app_send_status(String value, String desc) {
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
	
}
