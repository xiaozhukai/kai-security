package com.github.kai.security.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient
@EnableHystrix
@SpringBootApplication
@ServletComponentScan("com.github.kai.security.admin.config.druid")
public class KaiAdminApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(KaiAdminApplication.class).web(true).run(args);
    }
}
