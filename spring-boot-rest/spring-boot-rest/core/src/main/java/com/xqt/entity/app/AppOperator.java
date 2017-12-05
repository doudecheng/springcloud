package com.xqt.entity.app;

import java.io.Serializable;

/**
 * APP用户对象
 * @author andy
 */
public class AppOperator implements Serializable {

    /** */
    private static final long serialVersionUID = -5789922992070515459L;

    /** <code>session Id</code> */
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
