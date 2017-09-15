package com.github.kai.security.api.vo.user;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO:  用户信息
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 11:39
 */
public class UserInfo implements Serializable {
    public String id;               //ID
    public String username;         //用户名
    public String password;         //密码
    public String name;             //昵称
    private String description;     //描述
    private Date updTime;           //更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
}
