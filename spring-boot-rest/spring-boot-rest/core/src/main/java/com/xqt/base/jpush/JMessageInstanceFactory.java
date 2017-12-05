package com.xqt.base.jpush;

import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.crossapp.CrossAppClient;
import cn.jmessage.api.group.GroupClient;
import cn.jmessage.api.message.MessageClient;
import cn.jmessage.api.resource.ResourceClient;
import cn.jmessage.api.sensitiveword.SensitiveWordClient;
import cn.jmessage.api.user.UserClient;


/**
 * jmessage实例化工厂
 * @author andy
 */
public class JMessageInstanceFactory extends BaseJpush {

    /**
     * 初始化极光用户初始密码
     * @return
     */
    public static String initPwd(){
        return PWD;
    }

    /**
     * 实例化JMessageClient
     * @return
     */
    public static JMessageClient getJmessageClientInstance(){
        return new JMessageClient(APP_KEY,MASTER_SECRET);
    }

    /**
     * 实例化UserClient
     * @return
     */
    public static UserClient getUserClientInstance(){
        return new UserClient(APP_KEY,MASTER_SECRET);
    }

    /**
     * 实例化CrossAppClient
     * @return
     */
    public static CrossAppClient getCrossAppClientInstance(){
        return new CrossAppClient(APP_KEY,MASTER_SECRET);
    }

    /**
     * 实例化GroupClient
     * @return
     */
    public static GroupClient getGroupClientInstance(){
        return new GroupClient(APP_KEY, MASTER_SECRET);
    }

    /**
     * 实例化MessageClient
     * @return
     */
    public MessageClient getMessageClientInstance(){
        return new MessageClient(APP_KEY, MASTER_SECRET);
    }

    /**
     * 实例化ResourceClient
     * @return
     */
    public static ResourceClient getResourceClientInstance(){
        return new ResourceClient(APP_KEY, MASTER_SECRET);
    }

    /**
     * 实例化SensitiveWordClient
     * @return
     */
    public static SensitiveWordClient getSensitiveWordClientInstance(){
        return new SensitiveWordClient(APP_KEY, MASTER_SECRET);
    }
}