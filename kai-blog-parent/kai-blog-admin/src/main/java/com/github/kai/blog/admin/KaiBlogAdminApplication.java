package com.github.kai.blog.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient
@EnableHystrix
@SpringBootApplication
public class KaiBlogAdminApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(KaiBlogAdminApplication.class).web(true).run(args);
	}
}
