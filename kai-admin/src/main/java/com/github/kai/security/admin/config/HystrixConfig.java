package com.github.kai.security.admin.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;

/**
 * TODO: 监控Hystrix配置
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 23:14
 */
public class HystrixConfig {

    /**
     * TODO: 监控Hystrix服务
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 23:15
     */
    public HystrixMetricsStreamServlet hystrixMetricsStreamServlet(){
        return new HystrixMetricsStreamServlet();
    }

    /**
     * TODO: 服务注册
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 23:24
     */
    public ServletRegistrationBean registration(HystrixMetricsStreamServlet servlet){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(servlet);
        registrationBean.setEnabled(true);//是否启用该registrationBean
        registrationBean.addUrlMappings("/hystrix.stream");
        return registrationBean;
    }

}
