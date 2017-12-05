package com.xqt.util;


import com.xqt.util.json.JSONUtil;
import org.json.JSONException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;

/**
 * 
 * @ClassName: AppJsonUtil
 * @Description: App服务端返回参数工具类
 * @author LiangHua
 * @date 2017年2月9日 上午10:34:36
 * 
 */
public class AppJsonUtil{

    /**
     * Json返回数据
     * 
     * @param response
     * @param object
     */
    public static void writerJson(HttpServletResponse response, Object object) {
        try {
            writerJson(response, null, true, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Json返回数据
     * 
     * @param response
     * @param msg
     * @param state
     */
    public static void writerJson(HttpServletResponse response, String msg, boolean state) {
        try {
            writerJson(response, msg, state, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Json返回数据
     * 
     * @param response
     * @param msg
     * @param state
     * @param object
     */
    public static void writerJson(HttpServletResponse response, String msg, boolean state, Object object) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("state", state);
            if (!StringUtil.isEmpty(msg)) {
                result.put("msg", msg);
            }
            if (object != null) {
                result.put("data", object);
            }
            response.setContentType("application/json");
            writer(response, JSONUtil.toJSONString(result));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param response
     * @param msg
     * @param code
     * @param state
     * @param object
     */
    public static void writerJson(HttpServletResponse response, String msg,Integer code, boolean state, Object object) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("state", state);
            if (!StringUtil.isEmpty(msg)) {
                result.put("msg", msg);
            }
            if (object != null) {
                result.put("data", object);
            }
            if(null != code){
                result.put("code",code);
            }
            response.setContentType("application/json");
            writer(response, JSONUtil.toJSONString(result));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    /**
     * Json加密并签名返回数据
     * 
     * @param response
     * @param msg
     * @param state
     * @param resultMap
     */
    public static void writerSecJson(HttpServletResponse response, String msg, boolean state, Map<String, Object> resultMap) {
    	if(null == resultMap){
    		 writerJson(response, msg, state, null);
    	}else{
    		try {

                Map<String, Object> resultSecMap = new HashMap<String, Object>();
                // 遍历返回的参数
                Iterator<String> it = resultMap.keySet().iterator();
                // 签名用List
                List<String> signList = new ArrayList<String>();
                while (it.hasNext()) {
                    String key = it.next();
                    Object value = resultMap.get(key);
                    if (value == null) {
                        continue;
                    }
                    String jsonValue = null;
                    if (value instanceof String || value instanceof Integer || value instanceof Long) {
                        jsonValue = URLEncoder.encode(String.valueOf(value), "utf-8");
                    } else {
                        jsonValue = URLEncoder.encode(JSONUtil.toJSONString(value), "utf-8");
                    }

                    signList.add(jsonValue);// 添加到签名List
                    resultSecMap.put(key, jsonValue);// 原参数替换成密文
                }
                String signature = DataSecurityUtil.signature(signList);// 加密签名
                resultSecMap.put("sign", signature);

                writerJson(response, msg, state, resultSecMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
    }

    public static void writer(HttpServletResponse response, String str) {
        try {
            // 设置页面不缓存
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
