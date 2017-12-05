package com.xqt.util;


import com.xqt.util.json.JSONUtil;
import org.json.JSONException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author andy
 */
public class HtmlUtil {

	public static void writerJson(HttpServletResponse response, Object object) {
		try {
			response.setContentType("text/html;charset=utf-8");
			AppJsonUtil.writer(response, JSONUtil.toJSONString(object));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


}
