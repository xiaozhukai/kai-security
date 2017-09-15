package com.github.kai.security.admin.biz;

import com.github.kai.security.admin.entity.User;
import com.github.kai.security.admin.mapper.MenuMapper;
import com.github.kai.security.admin.mapper.UserMapper;
import com.github.kai.security.common.biz.BaseBiz;
import com.github.kai.security.common.constant.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * TODO: 用户业务层
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 22:58
 */
@Service
public class UserBiz extends BaseBiz<UserMapper,User> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void insertSelective(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    @Override
    public void updateSelectiveById(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.updateById(entity);
    }

    /**
     * TODO: 根据用户名获取用户信息
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 23:06
     */
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

    /**
     * TODO: 获取spring security中的实际用户ID
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 23:07
     */
/*    public int getSecurityUserId(SecurityContextImpl securityContextImpl) {
        org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) securityContextImpl.getAuthentication().getPrincipal();
        return this.getUserByUsername(securityUser.getUsername()).getId();
    }*/

}
