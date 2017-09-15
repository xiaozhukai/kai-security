package com.github.kai.security.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RestController;

@Configurable					//结构配置
@EnableAutoConfiguration		//Auto使用配置
@RestController					//Rest请求配置
@EnableConfigServer				//server使用配置
public class KaiConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaiConfigApplication.class, args);
	}
}
