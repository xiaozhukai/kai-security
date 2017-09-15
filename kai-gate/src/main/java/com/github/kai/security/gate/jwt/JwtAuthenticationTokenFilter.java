package com.github.kai.security.gate.jwt;

import com.github.kai.security.gate.service.GateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: Jwt的授权Token的过滤器
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 12:34
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private GateUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${gate.jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader(this.tokenHeader);

        if (authToken != null){
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            logger.info("checking authentication " + username);
            //判断用户名不为空 && 用户安全上下文引用不为空
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //用户详情
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    //设置鉴定信息
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //打印日志鉴定用户
                    logger.info("authenticated user " + username + ", setting security context");
                    //设置安全持有人配置
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    response.setStatus(401);
                    response.getWriter().print("Toekn error!");
                    return ;
                }
            }
        }
        //过滤链
        filterChain.doFilter(request,response);
    }
}
