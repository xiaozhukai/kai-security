package com.github.kai.security.gate.session;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;

/**
 * TODO: session 应用初始化
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 14:07
 */
@Service
public class SessionApplicationInitializer extends AbstractHttpSessionApplicationInitializer{

    /**
     * TODO: servlet添加listener监听
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 14:16
     */
    @Override
    protected void afterSessionRepositoryFilter(ServletContext servletContext) {
        //super.afterSessionRepositoryFilter(servletContext);
        servletContext.addListener(new HttpSessionEventPublisher());
    }
}
