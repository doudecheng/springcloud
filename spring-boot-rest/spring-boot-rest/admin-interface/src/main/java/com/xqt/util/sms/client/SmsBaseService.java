package com.xqt.util.sms.client;



import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xqt.util.PropertiesUtil;
/**
 * 短信接口
 * @author yangjian
 *
 */
public class SmsBaseService {

	private final static Logger log = LoggerFactory.getLogger(SmsBaseService.class);
	// 读取配置文件
			private static PropertiesUtil smsFile = null;
			static {
				smsFile = new PropertiesUtil("smsConfig.properties");
			}
	private static String accountSid=smsFile.readProperty("accountSid");
	private static String token=smsFile.readProperty("token");
	private static String appId=smsFile.readProperty("appId");
	private static String success=smsFile.readProperty("success");
	
	/**
	 * 发送短信接口（ucpaas短信平台接口）
	 * @param appIds(可有可无，若不给值则使用配置文件里配置的默认应用id)
	 * @param templateId（短信模板id）
	 * @param mobile（手机号）
	 * @param param（参数，多个参数则用逗号分割）
	 * @return
	 */
	public static boolean sendSms(String appIds,String templateId,String mobile,String param){
		boolean isOk=false;
		if(null!=appIds && !"".equals(appIds)){
			appId=appIds;
		}
		AbsRestClient client=new JsonReqClient();
		String result=client.templateSMS(accountSid, token, appId, templateId, mobile, param);
		log.info("sms_result:{},mobile:{}",result,mobile);
		if(null!=result && !"".equals(result)){
			JSONObject resultJson=new JSONObject(result);
			JSONObject resp=(JSONObject)resultJson.get("resp");
			String respCode=resp.getString("respCode");
			if(null!=respCode && success.equals(respCode)){
				isOk=true;
			}
		}
		return isOk;
	}	

}
