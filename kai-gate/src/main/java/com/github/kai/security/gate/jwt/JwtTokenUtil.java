package com.github.kai.security.gate.jwt;

import com.github.kai.security.api.vo.user.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: JwtToken 工具
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 12:49
 */
@Component
public class JwtTokenUtil implements Serializable{
    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${gate.jwt.secret}")
    private String secret;          //秘钥

    @Value("${gate.jwt.expiration}")
    private Long expiration;        //终结时间

    public String getUsernameFromToken(String token){
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * TODO: 获取请求权
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 12:58
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * TODO: 创建时间
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:01
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * TODO: 终结时间
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:03
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * TODO: 判断token是否过期
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:04
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * TODO: 判断密码最后一次更新时间是否有变动
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:05
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * TODO: 传入userinfo生成Token
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:08
     */
    public String generateToken(UserInfo info){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(CLAIM_KEY_USERNAME, info.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * TODO: 传入map需求生成token部件
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:12
     */
    String generateToken(Map<String, Object> claims){
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    /**
     * TODO: 判断Token能否重置
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:16
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset){
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && !isTokenExpired(token);
    }

    /**
     * TODO: 重置token
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:18
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * TODO: 校验Token
     *
     * Author: kai
     * CreateDate: 2017/9/5
     * CreateTime: 13:18
     */
    public Boolean validateToken(String token, UserDetails info) {
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        return (
                username.equals(info.getUsername())
                        && !isTokenExpired(token));
    }
}
