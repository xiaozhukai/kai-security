package com.github.kai.security.ui.controller;

import com.github.kai.security.ui.rpc.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO: 用户资源Controller
 *
 * Author: kai
 * CreateDate: 2017/9/11
 * CreateTime: 15:40
 */
@Controller
public class UserPermissionController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * TODO: 获取用户系统配置
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:41
     */
    @RequestMapping(value = "/user/system",method = RequestMethod.GET)
    @ResponseBody
    public String getUserSystem(){
        return userService.getSystemsByUsername(getCurrentUserName());
    }

    /**
     * TODO: 获取用户菜单
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:44
     */
    @RequestMapping(value = "/user/menu",method = RequestMethod.GET)
    @ResponseBody
    public String getUserMenu(@RequestParam(defaultValue = "-1") Integer parentId){
        return userService.getMenusByUsername(getCurrentUserName(),parentId);
    }
}
