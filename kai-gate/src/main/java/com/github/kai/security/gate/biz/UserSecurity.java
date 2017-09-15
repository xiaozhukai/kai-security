package com.github.kai.security.gate.biz;

import com.github.kai.security.api.vo.user.UserInfo;
import com.github.kai.security.gate.rpc.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * TODO: 安全用户
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 11:48
 */
@Service
public class UserSecurity {
    @Lazy               //懒加载
    @Autowired
    private IUserService userService;

    /**
     * TODO: 监控后台所有方法
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 12:02
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public UserInfo getUserByUsername(String username){
        return userService.getUserByUsername(username);
    }

    public UserInfo fallbackMethod(String username){
        return new UserInfo();
    }
}
