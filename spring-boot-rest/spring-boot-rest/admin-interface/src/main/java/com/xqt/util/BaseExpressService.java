package com.xqt.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSONObject;
import com.sdo.common.lang.StringUtil;

/**
 * 封装签名、验证及发送逻辑
 * 
 */
public class BaseExpressService {

	// 读取配置文件
	private static PropertiesUtil UTIL = null;
	static {
		UTIL = new PropertiesUtil("expressConfig.properties");
	}
	
	public static String httpSend(String url, NameValuePair[] params) throws IOException {
		System.out.println("------------- BaseExpressService.httpSend begin -------------");
		System.out.println("请求接口地址 : " + url);

		// 构建HttpClient
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams httpParams = client.getHttpConnectionManager().getParams();
		httpParams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

		// 设置请求参数列表
		postMethod.addParameters(params);

		// 签名<与参数列表顺序等无关，request body作为签名的明文>, 详细请参见《盛付通快捷支付API》--RSA签名
		RequestEntity requestEntity = postMethod.getRequestEntity();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		requestEntity.writeRequest(baos);
		String plainText = baos.toString();
		System.out.println("请求签名明文(request body) : " + plainText);
		String signMsg = RSA.sign(plainText,UTIL.readProperty("merchantPrivateKey"), "utf-8");

		// 设置签名类型与签名串到请求header里面
		postMethod.addRequestHeader("signType", UTIL.readProperty("rsaSignType"));
		postMethod.addRequestHeader("signMsg", signMsg);
		System.out.println("请求签名串 : " + signMsg);

		// 发起快捷请求
		int httpCode = client.executeMethod(postMethod);
		String responseBody = postMethod.getResponseBodyAsString();
		System.out.println("http请求响应状态码 : " + httpCode);
		System.out.println("http请求响应body : " + responseBody);

		// 验证签名，响应的签名类型与签名串同样也是从header里面去取
		Header responseSignMsgHeader = postMethod.getResponseHeader("signMsg");
		Header responseSignTypeHeader = postMethod.getResponseHeader("signType"); // 快捷API只会返回RSA
		if ((null != responseSignMsgHeader) && (null != responseSignTypeHeader)) {
			String responseSignType = responseSignTypeHeader.getValue();
			String responseSignMsg = responseSignMsgHeader.getValue();
			System.out.println("响应签名类型 : " + responseSignType);
			System.out.println("响应签名串 : " + responseSignMsg);

			boolean signResult = false;
			// 盛付通公钥
			if (StringUtil.equalsIgnoreCase("RSA", responseSignType)) {
				signResult = RSA.verify(responseBody, responseSignMsg, UTIL.readProperty("sftRsaPublicKey"), "utf-8");
			} else {
				System.out.println("未知的签名类型  : " + responseSignType);
			}

			if (signResult) {
				System.out.println("验证签名成功");
			} else {
				System.out.println("验证签名失败");
			}
		} else {
			System.out.println("找不到签名相关信息，验证签名失败");
		}
		System.out.println("------------- BaseExpressService.httpSend end -------------");
		return responseBody;
	}

	
	/**
	 * 发送请求并把返回结果封装成json对象
	 * @param params
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> doPost(NameValuePair[] params,String url)
			throws Exception {
		String responseBody = httpSend(url, params);

		return JSONObject.parseObject(responseBody);
	}
	
	public static String getRequestTimeString() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	// 商户号：商户在盛付通开通的商户号
    public static String merchantNo = UTIL.readProperty("merchantNo");
    // 报文编码，统一为UTF-8
    public static String utf8 = UTIL.readProperty("utf8");
    //商户系统支付成功回调地址, 可以运行test下的server.Jetty. java启动服务来测试，商户通过绑定域名映射到公网就可以完成回调
    public static String paymentNotifyUrl = UTIL.readProperty("paymentNotifyUrl");
    //创建支付 订单url
    public static String expressCreatePaymentOrderUrl = UTIL.readProperty("expressServiceUrl")+UTIL.readProperty("expressCreatePaymentOrderUrl");
    //支付预校验url
    public static String expressPrecheckForPaymentUrl =UTIL.readProperty("expressServiceUrl")+ UTIL.readProperty("expressPrecheckForPaymentUrl");
    //支付url
    public static String expressPaymentUrl =UTIL.readProperty("expressServiceUrl")+ UTIL.readProperty("expressPaymentUrl");
    //商户会员信息id
    //public static String outMemberId = UTIL.readProperty("outMemberId");
    //盛付通RSA公钥，场景：当盛付通响应报文给商户，商户可以拿此参数对盛付通响应内容验证签名，确保商户收到的内容是盛付通响应，保证安全
    public static String sftRsaPublicKey = UTIL.readProperty("sftRsaPublicKey");
    
}
