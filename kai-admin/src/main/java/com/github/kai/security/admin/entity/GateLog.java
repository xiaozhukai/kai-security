package com.github.kai.security.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * TODO: 资源的操作日志记录实体
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 16:07
 */
@Table(name = "gate_log")
public class GateLog {
    @Id
    private Integer id;             //表主键

    private String menu;            //页面菜单

    private String opt;             //页面选择

    private String uri;             //资源标识

    @Column(name = "crt_time")
    private Date crtTime;           //创建时间

    @Column(name = "crt_name")
    private String crtName;         //创建者昵称

    @Column(name = "crt_host")
    private String crtHost;         //创建地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
