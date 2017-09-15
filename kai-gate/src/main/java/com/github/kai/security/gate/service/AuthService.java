package com.github.kai.security.gate.service;

/**
 * TODO: 授权业务层
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 12:26
 */
public interface AuthService {
    /**
     * TODO: 登入
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 12:27
     */
    String login(String clientId, String secret);

    /**
     * TODO: 更新
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 12:27
     */
    String refresh(String oldToken);

    /**
     * TODO: 验证
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 12:27
     */
    Boolean validate(String token, String resource);
}
