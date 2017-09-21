package com.github.kai.security.common.constant;

/**
 * TODO: QQ参数配置
 *
 * Author: kai
 * CreateDate: 2017/9/17
 * CreateTime: 11:32
 */
public class QQConstamt {

    public static final String GET_QQ_CODE_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=APP_ID&redirect_uri=http://5607d18a.nat123.net/api/QQ/login&state=test&scope=get_user_info";
    public static final String GET_QQ_ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=APP_ID&client_secret=APP_SECRET&code=CODE&redirect_uri=http://5607d18a.nat123.net/api/QQ/auth";
    public static final String GET_QQ_OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=ACCESS_TOKEN";

    public static final String APP_QQ_ID = "1106343747";
    public static final String APP_QQ_SECRET = "djeYyJwN6H1I5LPg";
}
