package com.github.kai.security.gate.controller;

import com.github.kai.security.gate.jwt.JwtAuthenticationResponse;
import com.github.kai.security.gate.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO: RestController授权controller
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 21:05
 */
@RestController
@RequestMapping("/api/jwt")
public class AuthController {

    @Value("${gate.jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    /**
     * TODO: 创建token
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 21:22
     */
    @RequestMapping(value = "auth",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(String username,String password){
        //用户token登入
        final String token = authService.login(username,password);
        //返回Http的status为200,并带入这个token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    /**
     * TODO: 重置获取Token
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 21:22
     */
    @RequestMapping(value = "refresh",method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
        //获取用户的token
        String token = request.getHeader(tokenHeader);
        //获取重置的token
        String refreshedToken = authService.refresh(token);
        //判断重置后的token
        if (refreshedToken == null){
            return ResponseEntity.badRequest().body(null);
        }else{
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    /**
     * TODO: 核实token
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 21:30
     */
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ResponseEntity<?> verify(String token,String resource){
        //返回核实的成功失败信息
        if (authService.validate(token,resource)){
            //成功返回状态200,body返回true
            return ResponseEntity.ok(true);
        }else{
            //失败返回状态401，body返回false
            return ResponseEntity.status(401).body(false);
        }
    }

}
