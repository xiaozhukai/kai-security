package com.github.kai.security.monitor.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO: 登录验证
 *
 * Author: kai
 * CreateDate: 2017/9/11
 * CreateTime: 14:35
 */
@Controller
public class SecurityController {
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
