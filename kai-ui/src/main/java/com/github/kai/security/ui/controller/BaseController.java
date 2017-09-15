package com.github.kai.security.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO: 设置BaseController
 *
 * Author: kai
 * CreateDate: 2017/9/11
 * CreateTime: 14:53
 */
public class BaseController {
    @Autowired
    public HttpServletRequest request;

    /**
     * TODO: 获得当前用户名
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 14:55
     */
    public String getCurrentUserName(){
        String authorization = request.getHeader("Authorization");
        return new String(Base64Utils.decodeFromString(authorization));
    }
}
