package com.github.kai.security.gate.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.stereotype.Service;

/**
 * TODO: session 配置
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 14:22
 */
@Configuration
public class SessionConfig {
    /**
     * TODO: 配置redis配置
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 14:24
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction(){
        return ConfigureRedisAction.NO_OP;
    }
}
