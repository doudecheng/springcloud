package com.xqt.core.enums;

public class CommonEnum {

	/**
	 * Cookie的类型
	 * 
	 * @author LiangHua
	 */
	public enum cookie_type {
		TYPE_01("01", "用户session信息"), TYPE_02("02", "图片验证码"), TYPE_03("03",
				"短信验证码"), TYPE_04("04", "发送短信限权");
		// 枚举值
		private String value;
		// 描述
		private String desc;

		private cookie_type(String value, String desc) {
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

	/**
	 * 后台账号状态（1：启用、2：停用）
	 * 
	 * @author LiangHua
	 */
	public enum mem_status {
		STATUS_1(1, "启用"), STATUS_2(2, "停用");

		// 枚举值
		private Integer value;

		// 描述
		private String desc;

		private mem_status(Integer value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
	}

	/**
	 * 后台菜单级别
	 * 
	 * @author LiangHua
	 */
	public enum menu_grade {
		GRADE_1(1, "一级菜单"), GRADE_2(2, "二级菜单");

		// 枚举值
		private Integer value;

		// 描述
		private String desc;

		private menu_grade(Integer value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
	}

	/**
	 * 字符集
	 * 
	 * @author LiangHua
	 */
	public enum CharEncoding {
		UTF_8("UTF_8"), UTF_16("UTF_16"), GBK("GBK"), GB2312("GB2312"), ISO8859_1(
				"ISO8859_1");

		/**
		 * 枚举值
		 */
		private String value;

		private CharEncoding(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

}
