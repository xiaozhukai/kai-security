package com.github.kai.security.api.vo.log;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO: 日志信息
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 11:34
 */
public class LogInfo implements Serializable{
    private String menu;            //菜单

    private String opt;             //选择

    private String uri;             //uri路径定位

    private Date crtTime;           //创建时间

    private String crtUser;         //创建用户

    private String crtName;         //创建名称

    private String crtHost;         //创建地址

    public LogInfo() {
    }

    public LogInfo(String menu, String opt, String uri, Date crtTime, String crtUser, String crtName, String crtHost) {
        this.menu = menu;
        this.opt = opt;
        this.uri = uri;
        this.crtTime = crtTime;
        this.crtUser = crtUser;
        this.crtName = crtName;
        this.crtHost = crtHost;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    public String getCrtHost() {
        return crtHost;
    }

    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }
}
