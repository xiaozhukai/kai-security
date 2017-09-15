package com.github.kai.security.monitor;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.swing.event.HyperlinkEvent;


/**
 * TODO: 监视器
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 15:05
 */
@EnableHystrix
@EnableHystrixDashboard
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class KaiMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaiMonitorApplication.class, args);
	}

	/**
	 * TODO: 启动的时候加载Hystrix监控
	 *
	 * Author: kai
	 * CreateDate: 2017/9/6
	 * CreateTime: 15:04
	 */
	@Bean
	public HystrixCommandAspect hystrixAspect(){
		return new HystrixCommandAspect();
	}
}
