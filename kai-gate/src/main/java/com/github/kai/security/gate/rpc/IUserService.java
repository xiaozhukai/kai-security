package com.github.kai.security.gate.rpc;

import com.github.kai.security.api.vo.authority.PermissionInfo;
import com.github.kai.security.api.vo.user.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * TODO: IUserService AOP层
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 11:54
 */

@FeignClient("admin-back")
@RequestMapping("api")
public interface IUserService {

    /**
     * TODO: 通过用户名获取用户接口
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 11:56
     */
    @RequestMapping(value = "/user/username/{username}", method = RequestMethod.GET)
    public UserInfo getUserByUsername(@PathVariable("username") String username);

    /**
     * TODO: 通过用户名获取许可证信息(授权)
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 11:57
     */
    @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
    public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username);

    /**
     * TODO: 获取所有许可证信息(授权)
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 11:58
     */
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    List<PermissionInfo> getAllPermissionInfo();
}
