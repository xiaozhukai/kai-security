package com.github.kai.security.ui.controller;

import com.github.kai.security.ui.rpc.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * TODO: 主页Controller层
 *
 * Author: kai
 * CreateDate: 2017/9/11
 * CreateTime: 15:04
 */
@Controller
@RequestMapping("")
public class HomeController extends BaseController{

    @Autowired
    private IUserService userService;

    /**
     * TODO: index首页
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:25
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        //调用父类获取用户名并查询到用户信息
        map.put("user",userService.getUserByUsername(getCurrentUserName()));
        return "index";
    }

    /**
     * TODO: 关于
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:29
     */
    @RequestMapping(value = "about",method = RequestMethod.GET)
    public String about(){
        return "about";
    }

    /**
     * TODO: 用户列表
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:29
     */
    @RequestMapping(value = "user",method = RequestMethod.GET)
    public String user(){
        return "user/list";
    }

    /**
     * TODO: 用户修改操作
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:30
     */
    @RequestMapping(value = "user/edit",method = RequestMethod.GET)
    public String userEdit(){
        return "user/edit";
    }

    /**
     * TODO: 菜单列表
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:33
     */
    @RequestMapping(value = "menu",method = RequestMethod.GET)
    public String menu(){
        return "menu/list";
    }

    /**
     * TODO: 菜单修改
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:33
     */
    @RequestMapping(value = "menu/edit",method = RequestMethod.GET)
    public String menuEdit(){
        return "menu/edit";
    }

    /**
     * TODO: 团队列表
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:34
     */
    @RequestMapping(value = "group",method = RequestMethod.GET)
    public String group(){
        return "group/list";
    }

    /**
     * TODO: 团队用户
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:34
     */
    @RequestMapping(value = "group/user",method = RequestMethod.GET)
    public String groupUser(){
        return "group/user";
    }
    /**
     * TODO: 团队授权
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:35
     */
    @RequestMapping(value = "group/authority",method = RequestMethod.GET)
    public String groupAuthority(){
        return "group/authority";
    }

    /**
     * TODO: 团队修改
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:35
     */
    @RequestMapping(value = "group/edit",method = RequestMethod.GET)
    public String groupEdit(){
        return "group/edit";
    }

    /**
     * TODO: 团队类型
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:35
     */
    @RequestMapping(value = "groupType",method = RequestMethod.GET)
    public String groupType(){
        return "groupType/list";
    }

    /**
     * TODO: 团队类型修改
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:36
     */
    @RequestMapping(value = "groupType/edit",method = RequestMethod.GET)
    public String groupTypeEdit(){
        return "groupType/edit";
    }

    /**
     * TODO: 元素修改
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:36
     */
    @RequestMapping(value="element/edit",method = RequestMethod.GET)
    public String elementEdit(){
        return "element/edit";
    }

    /**
     * TODO: 获取到gateCilent列表
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:37
     */
    @RequestMapping(value = "gateClient",method = RequestMethod.GET)
    public String gateClient(){
        return "gateClient/list";
    }

    /**
     * TODO: gateCilent修改
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:38
     */
    @RequestMapping(value = "gateClient/edit",method = RequestMethod.GET)
    public String gateClientEdit(){
        return "gateClient/edit";
    }

    /**
     * TODO: gateCilent授权
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:39
     */
    @RequestMapping(value = "gateClient/authority",method = RequestMethod.GET)
    public String gateClientAuthority(){
        return "gateClient/authority";
    }

    /**
     * TODO:  gate日志
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:39
     */
    @RequestMapping(value = "gateLog",method = RequestMethod.GET)
    public String gateLog(){
        return "gateLog/list";
    }

    /**
     * TODO: 服务列表
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:39
     */
    @RequestMapping(value = "service",method = RequestMethod.GET)
    public String service(){
        return "service/list";
    }
}
