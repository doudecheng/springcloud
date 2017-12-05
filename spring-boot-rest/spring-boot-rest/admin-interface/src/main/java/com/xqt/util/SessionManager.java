package com.xqt.util;


import com.xqt.base.entity.Operator;
import com.xqt.constant.Globals;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SessionManager {

    /**
     * 读取配置文件
      */
    private static PropertiesUtil UTIL = null;
    static {
        UTIL = new PropertiesUtil("jedis.properties");
    }

    /**
     *  用户session缓存
      */
    public static final String COOKIE_KEY = "platSessionUser";

    /**
     * session存在时间
     */
    private static final Integer SESSION_EXPIRE = Integer.valueOf(UTIL.readProperty("session.expire"));

    /**
     * @function 将当前操作用户数据存入Redis，并生产cookie存入前端
     * @param request
     * @param response
     * @param obj
     * @param cookieType
     * @return return
     */
    public static String set(HttpServletRequest request,HttpServletResponse response, Object obj, String cookieType) {

        String ip = IpUtil.getIpAddr(request);
        String sessionId = DigestUtils.md5Hex(ip + Globals.UNDER_LINE
                + DateUtils.getDataString(DateUtils.yyyymmddhhmmss)
                + Globals.UNDER_LINE + RandomUtils.getRandomString(12));
        Cookie cookie = new Cookie(COOKIE_KEY + cookieType, sessionId);
        response.addCookie(cookie);
        // 用户的session信息
        if (obj instanceof Operator) {
            Operator operator = (Operator) obj;
            operator.setSessionId(sessionId);
            // session保持30分钟
            JedisUtil.setex(sessionId, SESSION_EXPIRE, operator);
            return sessionId;
        }
        // session保持30分钟
        JedisUtil.setex(sessionId, SESSION_EXPIRE, obj);
        return sessionId;
    }


    /**
     *
     * @Title: set
     * @Description: 将数据存入Redis
     * @param sessionId
     *            存放Redis的ID
     * @param timeout
     *            过期时间（分钟）
     * @param obj
     *            存放的数据
     */
    public static void set(String sessionId, int timeout, Object obj) {
        if (timeout > 0) {
            JedisUtil.setex(sessionId, timeout * 60, obj);
            return;
        }
        // session保持30分钟
        JedisUtil.setex(sessionId, SESSION_EXPIRE, obj);
    }

    /**
     * @function 将当前操作用户数据存入Redis
     * @param sessionId
     * @param operator
     * @return void
     */
    public static void set(String sessionId, Operator operator) {
        // session保持30分钟
        JedisUtil.setex(sessionId, SESSION_EXPIRE, operator);
    }

    /**
     *
     * @Title: get
     * @Description: 获取到redis信息
     * @param sessionId
     * @return
     */
    public static Object get(String sessionId) {
        return JedisUtil.get(sessionId);
    }

    /**
     * @function 获取当前操作用户数据
     * @param request
     * @return
     * @return SessionUser
     */
    public static Object get(HttpServletRequest request, String cookieType) {

        String sessionId = getSessionId(request, cookieType);

        if (!StringUtil.isEmpty(sessionId)) {
            return JedisUtil.get(sessionId);
        }
        return null;
    }

    /**
     * 获取用户对象
     * @param request
     * @param cookieType
     * @return
     */
    public static Object getOperator(HttpServletRequest request, String cookieType){
        String sessionId = getSessionId(request, cookieType);
        if("".equals(sessionId)){
            sessionId = getSessionIdByToken(request);
        }
        if (!StringUtil.isEmpty(sessionId)) {
            return JedisUtil.get(sessionId);
        }
        return null;
    }

    /**
     * @function 获取cookie的SESSIONID
     * @param request
     * @return
     * @return SessionUser
     */
    public static String getSessionId(HttpServletRequest request, String cookieType) {
        Cookie[] cookies = request.getCookies();
        String sessionId = "";
        String cookieKey = COOKIE_KEY + cookieType;
        if(null != cookies){
            for (Cookie cook : cookies) {
                if (cookieKey.equals(cook.getName())) {
                    sessionId = cook.getValue();
                }
            }
        }
        return sessionId;
    }

    /**
     * 前端自定义token
     * @param request
     * @return
     */
    public static String getSessionIdByToken(HttpServletRequest request){
        String sessionId = request.getHeader("X-Token");
        return sessionId;
    }

    /**
     * @function 重设用户session（刷新）session失效日期
     * @param request
     * @return
     * @return SessionUser
     */
    public static Long resetExpire(HttpServletRequest request, String cookieType) {

        Object obj = get(request, cookieType);

        if (obj != null && obj instanceof Operator) {
            Operator user = (Operator) obj;
            JedisUtil.expire(user.getSessionId().toString(), SESSION_EXPIRE);
        }
        return null;
    }

    /**
     * @function 删除缓存
     * @param key
     * @return
     * @return SessionUser
     */
    public static void del(String key) {
        if (!StringUtil.isEmpty(key)) {
            JedisUtil.del(key);
        }
    }
}
