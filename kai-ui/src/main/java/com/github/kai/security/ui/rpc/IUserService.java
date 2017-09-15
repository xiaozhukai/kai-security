package com.github.kai.security.ui.rpc;

import com.github.kai.security.api.vo.authority.PermissionInfo;
import com.github.kai.security.api.vo.user.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * TODO: FeignClient端-->admin-back
 *
 * Author: kai
 * CreateDate: 2017/9/11
 * CreateTime: 15:10
 */
@FeignClient("admin-back")          //调用admin模块
@RequestMapping("/api")             //网关API
public interface IUserService {
    /**
     * TODO: 调用admin模块-->获取用户信息
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:14
     */
    @RequestMapping(value = "/user/username/{username}",method = RequestMethod.GET)
    public UserInfo getUserByUsername(@PathVariable("username") String username);

    /**
     * TODO: 调用admin模块-->获取资源
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:16
     */
    @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
    public List<PermissionInfo> getPermissionByUserId(@PathVariable("username") String username);

    /**
     * TODO: 调用admin模块-->获取系统配置信息
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:18
     */
    @RequestMapping(value = "/user/un/{username}/system", method = RequestMethod.GET)
    public String getSystemsByUsername(@PathVariable("username") String username);

    /**
     * TODO: 调用admin模块--> 获取菜单
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:20
     */
    @RequestMapping(value = "/user/un/{username}/menu/parent/{parentId}", method = RequestMethod.GET)
    public String getMenusByUsername(@PathVariable("username") String username, @PathVariable("parentId") Integer parentId);

}
