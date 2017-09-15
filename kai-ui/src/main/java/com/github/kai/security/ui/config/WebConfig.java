package com.github.kai.security.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO: 配置MVC的适配器
 *
 * Author: kai
 * CreateDate: 2017/9/11
 * CreateTime: 14:38
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    /**
     * TODO: 添加头部适配器
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 14:44
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/*");
        super.addResourceHandlers(registry);
    }
}
