package com.github.kai.security.gate.jwt;

import java.io.Serializable;

/**
 * TODO: Json web token (JWT)，基于JSON的开放标准（(RFC 7519).该token被设计为紧凑且安全的
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 12:30
 */
public class JwtAuthenticationResponse implements Serializable{

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return  this.token;
    }
}
