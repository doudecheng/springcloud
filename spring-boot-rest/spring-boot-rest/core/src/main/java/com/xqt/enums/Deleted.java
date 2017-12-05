package com.xqt.enums;

/**
 * Created by Administrator on 2017/3/20.
 */
public enum Deleted {
    DELETED_NO(0, "未删除"), DELETED_YES(1, "已删除");
    public int key;
    public String value;

    private Deleted(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
