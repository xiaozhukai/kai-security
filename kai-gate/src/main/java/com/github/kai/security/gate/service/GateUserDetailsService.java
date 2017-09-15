package com.github.kai.security.gate.service;

import com.github.kai.security.api.vo.user.UserInfo;
import com.github.kai.security.gate.biz.UserSecurity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO:  用户进入服务大门
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 11:53
 */
@Service
public class GateUserDetailsService implements UserDetailsService {

    @Autowired
    private UserSecurity userSecurity;

    /**
     * TODO: 通过用户名加载用户
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 11:53
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //用户名判空
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }
        UserInfo info = userSecurity.getUserByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();    //获取到所有授权的信息
        return new User(
                username,   //用户名
                info.getPassword(), //密码
                true, // 是否可用
                true, // 是否过期
                true, // 证书不过期为true
                true, // 账户未锁定为true
                authorities);   //授权信息
    }
}
