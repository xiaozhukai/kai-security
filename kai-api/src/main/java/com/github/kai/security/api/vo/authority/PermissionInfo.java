package com.github.kai.security.api.vo.authority;

import java.io.Serializable;

/**
 * TODO: 许可证信息(授权)
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 11:17
 */
public class PermissionInfo implements Serializable{
    private String code;                //编码
    private String type;                //类型
    private String uri;                 //uri路径定位
    private String method;              //方法
    private String name;                //名称
    private String menu;                //菜单

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
