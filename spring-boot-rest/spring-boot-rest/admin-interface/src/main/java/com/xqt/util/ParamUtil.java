package com.xqt.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 处理请求参数
 * @author Andy
 *
 */
public class ParamUtil {

	/*
	 * http GET方法参数
	 */
	public static JSONObject getParams(HttpServletRequest request){
		String param = request.getParameter("param");
		if(!StringUtils.isEmpty(param)){
			String paramTmp = new String(Base64.decode(param));
			System.out.println(String.valueOf(paramTmp));
			try {
				param = URLDecoder.decode(paramTmp, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return JSONObject.parseObject(param);
		}else{
			return null;
		}
	}
	
	
}
