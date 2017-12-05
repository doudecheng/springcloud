package com.xqt.util;


import com.xqt.entity.app.AppOperator;

public final class AppSessionLocalUtils {

    // 入驻企业员工用户缓存
    private static final ThreadLocal<AppOperator> appThreadLocal = new ThreadLocal<AppOperator>();

    /**
     * 
     * @Title: setAppOperator
     * @Description: 设置入驻企业员工用户缓存
     * @author LiangHua
     * @param appOperator
     * @throws
     */
    public static void setOperator(AppOperator appOperator) {

        appThreadLocal.set(appOperator);

    }

    /**
     * 
     * @Title: getAppOperator
     * @Description: 获取入驻企业员工用户缓存
     * @author LiangHua
     * @return
     * @throws
     */
    public static AppOperator getOperator() {

        if (appThreadLocal != null){
            return appThreadLocal.get();
        }else{
            throw new RuntimeException("SESSION 为空");
        }

    }
}