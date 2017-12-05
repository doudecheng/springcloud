package com.xqt.entity.demo;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 *
 * @author andy
 * @create 2017-11-17 14:12
 **/
public class DemoMenu implements Serializable {

    private static final long serialVersionUID = 8827777895796496546L;

    private String path;

    private String name;

    private String icon;

    private List<DemoMenu> children; // 下级菜单列表

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List <DemoMenu> getChildren() {
        return children;
    }

    public void setChildren(List <DemoMenu> children) {
        this.children = children;
    }
}
