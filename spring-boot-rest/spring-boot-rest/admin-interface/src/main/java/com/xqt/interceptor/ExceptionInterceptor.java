package com.xqt.interceptor;


import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xqt.util.HtmlUtil;

/**
 * 
 * @ClassName: ExceptionInterceptor
 * @Description: 异常拦截器
 * @author LiangHua
 * @date 2017年2月4日 下午4:26:52
 * 
 */
public class ExceptionInterceptor extends HandlerInterceptorAdapter {
    final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            String msg = "";
            if (ex instanceof NullPointerException) {
                msg = "空指针异常";
            } else if (ex instanceof IOException) {
                msg = "文件读写异常";
            } else {
                msg = "连接异常，请重试";
            }
            logger(request, handler, ex);
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("success", false);
            result.put("msg", msg);
            HtmlUtil.writerJson(response, result);
        } else {
            super.afterCompletion(request, response, handler, ex);
        }
    }

    /**
     * 记录日志
     * 
     * @param request
     * @param handler
     * @param ex
     */
    public void logger(HttpServletRequest request, Object handler, Exception ex) {
        StringBuffer msg = new StringBuffer();
        msg.append("异常拦截日志");
        msg.append("[uri＝").append(request.getRequestURI()).append("]");
        Enumeration<String> enumer = request.getParameterNames();
        while (enumer.hasMoreElements()) {
            String name = (String) enumer.nextElement();
            String[] values = request.getParameterValues(name);
            msg.append("[").append(name).append("=");
            if (values != null) {
                int i = 0;
                for (String value : values) {
                    i++;
                    msg.append(value);
                    if (i < values.length) {
                        msg.append("｜");
                    }
                }
            }
            msg.append("]");
        }
        logger.error(msg.toString());
    }

}
