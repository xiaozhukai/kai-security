package com.github.kai.security.gate;

import com.github.kai.security.gate.utils.DBLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * TODO: 用户授权启动类
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 10:41
 */
@SpringBootApplication			//Boot启动
@EnableDiscoveryClient			//授权发现客户端
@EnableFeignClients				//授权RPC客户端
@EnableZuulProxy				//授权网关代理
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)	//session缓存
public class KaiGateApplication {

	public static void main(String[] args) {
		DBLog.getInstance().start();
		SpringApplication.run(KaiGateApplication.class, args);
	}
}
