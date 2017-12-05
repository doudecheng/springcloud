package com.xqt.controller;

import com.xqt.base.entity.Operator;
import com.xqt.common.PcsResult;
import com.xqt.enums.EnumPcsResultCode;
import com.xqt.util.HtmlUtil;
import com.xqt.util.SessionLocalUtils;
import com.xqt.util.SysConfig;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	/**
	 * 预处理数据
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		// 设置请求
		this.request = request;
		this.response = response;
	}

	/**
	 * 所有ActionMap 统一从这里获取
	 * 
	 * @return
	 */
	public Map<String, Object> getRootMap(HttpServletRequest request) {
		Map<String, Object> rootMap = new HashMap<>();
		// 添加url到 Map中
		rootMap.put("baseUrl", SysConfig.baseUrl);

		Enumeration<String> enm = request.getParameterNames();
		while (enm != null && enm.hasMoreElements()) {
			String key = enm.nextElement().toString();
			rootMap.put(key, request.getParameter(key));
		}

		// 获取当前操作用户
		Operator operator = SessionLocalUtils.getOperator();
		if (operator != null) {
			rootMap.put("operator", operator);
		}
		return rootMap;
	}

	/**
	 * 将Post流传入的json参数转化为对象
	 * 
	 * @param classType
	 * @return
	 * @throws IOException
	 */
	public <T> Object getJsonParams(Class<T> classType) {
		Map<String,Object> map_=new HashMap<>(16);
		Map<String,Object> map;
		try {
			StringBuilder sb = getStringBuilder();
			map=JSONObject.fromObject(URLDecoder.decode(sb.toString(), "utf-8"));
			map.forEach((key,value) -> {
				if(!"".equals(key)){
					map_.put(key,value);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(JSONObject.fromObject(map_),classType);
	}


	/**
	 * 将Post流传入的json参数转化为jsonobject
	 */
	public JSONObject getJsonObject(){
		JSONObject o = null;
		try {
			StringBuilder sb = getStringBuilder();
			if(null != sb){
				o = JSONObject.fromObject(URLDecoder.decode(sb.toString(), "utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return o;
	}


	public StringBuilder getStringBuilder() throws IOException{
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb;
	}

	/**
	 * 跳转view
	 * 
	 * @param viewName
	 * @param context
	 * @return
	 */
	public ModelAndView forword(String viewName, Map context) {
		return new ModelAndView(viewName, context);
	}

	/**
	 * 重定向到新的页面
	 * 
	 * @param request
	 * @param response
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {

		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath();
		response.sendRedirect(basePath + url);
	}

	/**
	 * 
	 * 返回列表数据
	 * 
	 */
	public void sendListData(HttpServletResponse response, int rowCount,int pageCount,Object rows) {
		Map<String, Object> result = new HashMap<>(3);
		result.put("rowCount", rowCount);
		result.put("pageCount", pageCount);
		result.put("rows", rows);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 
	 * 提示成功信息
	 * 
	 * @param message
	 * 
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		HtmlUtil.writerJson(response, new PcsResult(EnumPcsResultCode.SUCCESS,message));
	}

	/**
	 *	提示成功信息
	 * @param response
	 * @param message
	 * @param o
	 */
	public void sendSuccessMessage(HttpServletResponse response,String message,Object o) {
		HtmlUtil.writerJson(response, new PcsResult(EnumPcsResultCode.SUCCESS, message, o));
	}

	/**
	 * 自定义返回信息
	 * @param response
	 * @param pcsResult
	 */
	public void sendResultMessage(HttpServletResponse response,PcsResult pcsResult){
		HtmlUtil.writerJson(response, pcsResult);
	}


	/**
	 * 提示失败信息
	 * @param response
	 * @param message
	 * @param code
	 */
	public void sendFailureMessage(HttpServletResponse response, String message,EnumPcsResultCode code) {
		HtmlUtil.writerJson(response, new PcsResult(code,message));
	}
}
