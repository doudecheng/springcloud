package com.xqt.interceptor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqt.annotation.Duplicate;
import com.xqt.constant.Globals;
import com.xqt.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 重复提交拦截器
 * 
 * @author 梁华
 * 
 */
public class DuplicateSubmitInterceptor extends HandlerInterceptorAdapter {

	private static final String COOKIE_TOKEN = "COOKIE_TOKEN";

	private static final String IS_FRIST_ONCLICK = "0";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 仅拦截对方法的访问，对于其他静态资源不进行拦截
		if (handler instanceof HandlerMethod) {

			HandlerMethod method = (HandlerMethod) handler;
			Duplicate duplicate = method.getMethod().getAnnotation(Duplicate.class);

			// 如果防重复提交不为空
			if (duplicate != null) {

				// 需要添加Token
				if (duplicate.add()) {
					setToken(request, response);
				}
				// 如果是需要验证Token
				if (duplicate.validate()) {
					// 验证不通过，则拦截
					if (!validateToken(request)) {
						Map<String, Object> data = new HashMap<String, Object>();
						data.put("success", false);
						data.put("msg", "操作频繁，请"+ SysConfig.duplicateOnclickTime + "秒后再试");
						HtmlUtil.writerJson(response, data);
						return false;
					}
				}
			}
		}

		return super.preHandle(request, response, handler);
	}

	/**
	 * @function 将存入Redis的Token取出，若不为空，则表示需要刷新页面
	 * @param request
	 * @return void
	 */
	private boolean validateToken(HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		String key = "";
		if (cookies == null) {
			return false;
		}
		for (Cookie cook : cookies) {
			if (COOKIE_TOKEN.equals(cook.getName())) {
				key = cook.getValue();
				break;
			}
		}

		// 获取redis中存的值
		Object obj = JedisUtil.get(key);
		if (obj == null) {
			return false;
		}
		// 第一次点击则直接过，后续每次点击都需要判断是否在重复点击时间内
		String[] token = String.valueOf(obj).split(Globals.UNDER_LINE);
		if (!IS_FRIST_ONCLICK.equals(token[0])) {
			Long clickTime = Long.valueOf(token[1]);
			Long nowTime = Calendar.getInstance().getTime().getTime();
			if (nowTime - clickTime < SysConfig.duplicateOnclickTime * 1000) {
				return false;
			}
		}

		JedisUtil.set(key, 1 + Globals.UNDER_LINE
				+ Calendar.getInstance().getTime().getTime());
		return true;
	}

	/**
	 * @function 将生成的Token存入Redis，并生产cookie存入前端
	 * @param request
	 * @param response
	 * @return void
	 */
	private void setToken(HttpServletRequest request,
			HttpServletResponse response) {

		// 生成Token
		String token = UUIDGenerator.generate();
		// 生成cookieId
		String ip = IpUtil.getIpAddr(request);
		String cookieId = DigestUtils.md5Hex(ip + Globals.UNDER_LINE
				+ DateUtils.getDataString(DateUtils.yyyymmddhhmmss)
				+ Globals.UNDER_LINE + token);
		Cookie cookie = new Cookie(COOKIE_TOKEN, cookieId);
		response.addCookie(cookie);
		// token存入redis
		JedisUtil.set(cookieId, IS_FRIST_ONCLICK + Globals.UNDER_LINE
				+ Calendar.getInstance().getTime().getTime());
	}
}
