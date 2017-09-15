package com.github.kai.security.gate.service;

import com.github.kai.security.api.vo.authority.PermissionInfo;
import com.github.kai.security.api.vo.user.UserInfo;
import com.github.kai.security.gate.jwt.JwtTokenUtil;
import com.github.kai.security.gate.rpc.IUserService;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.sun.istack.internal.Nullable;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * TODO: 授权业务层实现
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 12:28
 */
@Service
public class AuthServiceImpl implements AuthService {
    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;
    //跨平台密码加密
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthServiceImpl(JwtTokenUtil jwtTokenUtil, IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    /**
     * TODO: 登入业务实现
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 21:39
     */
    @Override
    public String login(String username, String password) {
        UserInfo info = userService.getUserByUsername(username);
        String token = "";
        if (encoder.matches(password,info.getPassword())){
            token = jwtTokenUtil.generateToken(info);
        }
        return token;
    }

    /**
     * TODO: 更新业务实现
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 21:39
     */
    @Override
    public String refresh(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo info = userService.getUserByUsername(username);
        //判断更换token条件是否到了
        if (jwtTokenUtil.canTokenBeRefreshed(token, info.getUpdTime())) {
            //更换新的token
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    /**
     * TODO: 验证业务实现
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 21:39
     */
    @Override
    public Boolean validate(String token, String resource) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo info = userService.getUserByUsername(username);
        return info.getUsername().equals(username) && !jwtTokenUtil.isTokenExpired(token) && validateResource(username,resource);
    }

    /**
     * TODO: 校验数据
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 22:07
     */
    public Boolean validateResource(String username, String resource) {
        String [] res = resource.split(":");
        final String requestUri = res[0];
        final String method = res[1];
        List<PermissionInfo> clientPermissionInfo = userService.getPermissionByUsername(username);

        Collection<PermissionInfo> result = Collections2.filter(clientPermissionInfo, new Predicate<PermissionInfo>() {
            @Override
            public boolean apply(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}","[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                //正则匹配请求uri
                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/")) && method.equals(permissionInfo.getMethod());
            }
        });
        //返回集合的长度
        if (result.size() <= 0){
            return false;
        }
        return true;
    }
}
