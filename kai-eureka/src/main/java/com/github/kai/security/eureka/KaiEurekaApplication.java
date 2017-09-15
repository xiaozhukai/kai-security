package com.github.kai.security.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * TODO: eureka server注册中心启动类
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 15:53
 */
@EnableEurekaServer
@SpringBootApplication
public class KaiEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaiEurekaApplication.class, args);
	}
}
