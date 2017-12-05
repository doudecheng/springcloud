package com.xqt.enums;

public class CommonEnum {
	
	/**
	 * 
	 * @author andy
	 *
	 */
	public enum Deleted {  //删除标识
		DELETED_NO("1", "未删除"), DELETED_YES("-1", "已删除");
	    public String key;
	    public String value;
	    private Deleted(String key, String value) {
	        this.key = key;
	        this.value = value;
	    }

	    public String getKey() {
	        return key;
	    }

	    public void setKey(String key) {
	        this.key = key;
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }
	}
	
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
	 * 用户登录状态（-1：禁用、2：启用）
	 * 
	 * @author 
	 */
	public enum mem_status {
		STATUS_(-1, "禁用"), STATUS_1(1, "启用");

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
	 * 发送验证码判断手机号是否存在的不同判断方式（-1：注册、2：其他公用）
	 * 
	 * @author 
	 */
	public enum send_verifycode {
		TYPE_1("1", "注册"), TYPE_2("2", "其他公用");

		// 枚举值
		private String value;

		// 描述
		private String desc;

		private send_verifycode(String value, String desc) {
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
	 * app用户登录状态（已删除：-1正常：1禁止登录：2）
	 * 
	 * @author 
	 */
	public enum app_login_status {
		STATUS_(-1, "已删除"), STATUS_1(1, "正常"), STATUS_2(2, "禁止登录");

		// 枚举值
		private Integer value;

		// 描述
		private String desc;

		private app_login_status(Integer value, String desc) {
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
	 * app用户登录历史  登录是否成功，失败：-1成功：1
	 * 
	 * @author 
	 */
	public enum appLogin_isSuccess {
		ISSUCCESS_("-1", "失败"), ISSUCCESS_1("1", "成功");

		// 枚举值
		private String value;

		// 描述
		private String desc;

		private appLogin_isSuccess(String value, String desc) {
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
	 * app用户登录历史  登录方式，验证码：1口令：2手势：3指纹：4
	 * 
	 * @author 
	 */
	public enum appLogin_type {
		TYPE_1("1", "验证码"), TYPE_2("2", "口令"),TYPE_3("3", "手势"),TYPE_4("4", "指纹");

		// 枚举值
		private String value;

		// 描述
		private String desc;

		private appLogin_type(String value, String desc) {
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
	 * 
	 * @ClassName:     CommonEnum.java
	 * @Description:   会员套餐  时间单位
	 * @author         Dongxuejiao
	 * @Date           2017年6月22日 下午2:09:39
	 */
	public enum time_unit {
	       UNIT_1("1", "天"), UNIT_2("2", "月"), UNIT_3("3", "年");
	       // 枚举值
	       private String value;

	       // 描述
	       private String desc;

	       private time_unit(String value, String desc) {
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
	 * 接口操作code定义
	 */
	public enum result_code{
		SUCCESS(0,"成功"),ERROR(1,"失败"),FORBIDLOGIN(2,"登录过期"),OUTLOGIN(3,"强制退出系统");
		// 枚举值
		private Integer value;

		// 描述
		private String desc;

		private result_code(Integer value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer getValue() {
			return value;
		}

	}


	 /**
	  * 
	  * enum:           consumption_status
	  * @Description:   账户表消费状态
	  * @author         Dongxuejiao
	  * @Date           2017年6月12日 下午6:16:29
	  */
   	public enum consumption_status {
       STATUS_1("1", "未支付"), STATUS_2("2", "已支付"), STATUS_3("3", "支付失败");
       // 枚举值
       private String value;

       // 描述
       private String desc;

       private consumption_status(String value, String desc) {
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
   
   	public enum deal_status{
	   STATUS_("-1","待支付"),STATUS_1("1","支付中"),STATUS_2("2","支付成功"),STATUS_3("3","支付失败");
	   // 枚举值
       private String value;

       // 描述
       private String desc;

       private deal_status(String value, String desc) {
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
	  * 
	  * enum:           pay_type
	  * @Description:   支付类型
	  * @author         Dongxuejiao
	  * @Date           2017年6月12日 下午6:16:29
	  */
    public enum pay_type {
    	TYPE_00("00","后台充值"),TYPE_01("1", "微信"), TYPE_02("2", "支付宝"), TYPE_03("3", "网银"),TYPE_04("4", "快捷支付");
        // 枚举值
        private String value;

        // 描述
        private String desc;

        private pay_type(String value, String desc) {
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
	  * 
	  * enum:           pay_type
	  * @Description:   支付状态
	  * @author         andy
	  * 第三方支付接口返回的支付状态均跟这个状态进行匹配
	  */
   public enum payment_status {
       STATUS0("0", "待支付"), STATUS1("1", "支付成功"), STATUS2("2", "支付失败"), STATUS3("3", "支付中");
       // 枚举值
       private String value;

       // 描述
       private String desc;

       private payment_status(String value, String desc) {
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
    * 
    * @ClassName:     CommonEnum.java
    * @Description:   订单类型 1:开通VIP2：续费3：升级
    * @author         Dongxuejiao
    * @Date           2017年6月29日 下午7:07:17
    */
   public enum orderType{
   	
   	TYPE_1("1", "开通"),TYPE_2("2", "续费"),TYPE_3("3", "升级"),;
   	// 枚举值
       private String value;

       // 描述
       private String desc;

       private orderType(String value, String desc) {
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
     * 
     * @ClassName: PayStatus
     * @Description: 支付状态
     * 
     */
    public enum Pay_Status {
        STATUS_01("01", "已下单"), STATUS_02("02", "已完成"), STATUS_03("03", "已取消"), STATUS_04("04", "支付失败");
        // 枚举值
        private String value;

        // 描述
        private String desc;

        private Pay_Status(String value, String desc) {
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
     * 
     * @Description:   短信验证码 
     * @author         Dongxuejiao
     * @Date           2017年6月13日 下午8:27:08
     */
    public enum SMS_TYPE {
        TYPE_01("81756", "短信验证码"), TYPE_02("02", "注册成功"),TYPE_03("81757", "自动续费提醒"),TYPE_04("160324", "手动续费提醒");

        // 枚举值
        private String value;

        // 描述
        private String desc;

        private SMS_TYPE(String value, String desc) {
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
     * 
     * @ClassName: TradeType
     * @Description: 交易来源
     * 
     */
    public enum TradeType {
        NATIVE, APP
    }
    
    
    public enum socketCode{
    	
    	NORMAL("000", "正常"), OFFSITE_LOGIN("001", "异地登录"),OFFVIP("002","VIP到期提醒"),INFORMATION("003","资讯信息"),HISTORY("004","黑金池历史消息"),HEART_JUMP("777","心跳消息");
    	// 枚举值
        private String value;

        // 描述
        private String desc;

        private socketCode(String value, String desc) {
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
    
    
    //货币类型
    public enum currency{
    	
    	CNY("CNY","人民币"),
    	USD("USD","美元"),
    	GBP("GBP","英镑"),
    	HKD("HKD","港币"),
    	SGD("SGD","新加坡"),
    	JPY("JPY","日元"),
    	CAD("CAD","加拿大"),
    	AUD("AUD","澳元"),
    	EUR("EUR","欧元"),
    	CHF("CHF","瑞士法郎");
    	
    	// 枚举值
        private String value;

        // 描述
        private String desc;
        
    	private currency(String value,String desc){
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
    
    //银行类型
    public enum bankCardType{
    	
    	DR("DR","借记卡"),
    	CR("CR","贷记卡");
    	
    	
    	// 枚举值
        private String value;

        // 描述
        private String desc;
        
    	private bankCardType(String value,String desc){
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
    
    //证件类型
    public enum idType{
    	IC("IC","身份证"),
    	TIC("TIC","临时身份证"),
    	PP("PP","护照"),
    	SC("SC","士兵证"),
    	AOC("AOC","军官证"),
    	ACC("ACC","军人文职干部证"),
    	POC("POC","警官证"),
    	APC("APC","武警证"),
    	HMP("HMP","港澳居民来往内地通行证"),
    	RB("RB","户口簿");// 枚举值
    	
        private String value;

        // 描述
        private String desc;
        
    	private idType(String value,String desc){
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
     * 角色
     * @author Andy
     *
     */
    public enum role_type{
    	plat("1","平台"),business("2","加盟商企业");
    	
    	 private String value;
         // 描述
         private String desc;
         
         private role_type(String value,String desc){
        	 this.value = value;
             this.desc = desc;
         }

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
    }

}
