package com.github.kai.blog.admin.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * TODO: mybatis mapper 扫描配置类
 *
 * Author: kai
 * CreateDate: 2017/9/12
 * CreateTime: 13:15
 */
@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MapperConfiguration  implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    private String basePackage;

    /**
     * TODO: 扫描配置器
     *
     * Author: kai
     * CreateDate: 2017/9/12
     * CreateTime: 13:18
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }

    /**
     * TODO: 设置环境配置
     *
     * Author: kai
     * CreateDate: 2017/9/12
     * CreateTime: 13:21
     */
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, null);
        this.basePackage = propertyResolver.getProperty("mybatis.basepackage");
    }




}
