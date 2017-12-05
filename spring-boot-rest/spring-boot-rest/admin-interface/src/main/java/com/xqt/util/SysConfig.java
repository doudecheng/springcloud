package com.xqt.util;


/**
 * @author 123
 */
public class SysConfig {

    /**
     * 读取配置文件sysConfig
      */
    private static PropertiesUtil UTIL = null;
    static {
        UTIL = new PropertiesUtil("sysConfig.properties");
    }

    /**
     * 可重复点击时间间隔5S
      */
    public static int duplicateOnclickTime = Integer.valueOf(UTIL.readProperty("duplicate_onclick_time"));

    /**
     * 默认密码
     */
    public static String defaultPwd = UTIL.readProperty("defaultPwd");

    /**
     * 项目根路径
     */
    public static String baseUrl = UTIL.readProperty("base_url");

    /**
     * 文件上传物理根路径
     */
    public static String fileUploadPath = UTIL.readProperty("file_upload_path");

    /**
     * 文件上传后网络访问根路径
     */
    public static String nginxRootUrl = UTIL.readProperty("nginx_root_url");

    /**
     * 图片压缩比率
     */
    public static String imageLevelRate =  UTIL.readProperty("image_level_rate");

    /**
     * 线程池初始化大小
     */
    public static Integer threadDefaultSize = Integer.valueOf(UTIL.readProperty("thread_default_size"));

    /**
     * 线程池最大线程
     */
    public static Integer threadMaxSize = Integer.valueOf(UTIL.readProperty("thread_max_size"));

    /**
     * 增加线程阀值，任务线程比率：任务/线程
     */
    public static Integer threadAddThreshold = Integer.valueOf(UTIL.readProperty("thread_add_threshold"));

    /**
     * 减少线程阀值，任务线程比率：任务/线程
      */
    public static Integer threadRemoveThreshold = Integer.valueOf(UTIL
            .readProperty("thread_remove_threshold"));
}
