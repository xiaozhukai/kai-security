package com.github.kai.security.gate.config;

import com.github.kai.security.gate.jwt.JwtAuthenticationTokenFilter;
import com.github.kai.security.gate.service.GateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * TODO: web 页面安全验证配置
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 12:03
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private GateUserDetailsService detailsService;
    @Value("${gate.ignore.startWith}")
    private String startWith;

    /**
     * TODO: 设置http传输config配置
     *
     * Author: kai
     * CreateDate: 2017/9/7
     * CreateTime: 23:57
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").defaultSuccessUrl("/admin/index").permitAll().and()
                .logout().logoutSuccessUrl("/login").invalidateHttpSession(true).and().authorizeRequests()
                .antMatchers("/**/*.css", "/img/**", "/**/*.js","/api/**","/*/api/**") // 放开"/api/**",通过oauth2.0来鉴权
                .permitAll().and().authorizeRequests().antMatchers("/**").authenticated();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.httpBasic();
        // 添加JWT filter
        http .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        http.headers().cacheControl();
        http.headers().contentTypeOptions().disable();
    }

    /**
     * TODO: 授权配置
     *
     * Author: kai
     * CreateDate: 2017/9/8
     * CreateTime: 0:01
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * TODO: Jwt的TokenFilter
     *
     * Author: kai
     * CreateDate: 2017/9/8
     * CreateTime: 0:01
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
}
