package com.github.kai.security.gate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO: 安全登入Controller
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 22:20
 */
@Controller
public class SecurityController {
    /**
     * TODO: login请求直接返回登录页面
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 22:21
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
