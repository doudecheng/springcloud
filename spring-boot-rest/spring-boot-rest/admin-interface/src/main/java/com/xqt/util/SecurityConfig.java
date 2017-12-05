package com.xqt.util;



public class SecurityConfig {

    // 读取配置文件SecurityConfig
    private static PropertiesUtil UTIL = null;
    static {
        UTIL = new PropertiesUtil("SecurityConfig.properties");
    }

    // 签名私钥
    public static String secKey = UTIL.readProperty("sec_key");

    // 加解密报文用Key
    public static String cryptKey = UTIL.readProperty("crypt_Key");
}
