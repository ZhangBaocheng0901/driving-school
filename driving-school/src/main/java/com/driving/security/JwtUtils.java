// src/main/java/com/driving/security/JwtUtils.java
package com.driving.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret; // 从配置文件读取密钥

    @Value("${jwt.expiration}")
    private long jwtExpirationMs; // 从配置文件读取过期时间

    private final String CLAIM_USER_ID = "user_id";
    private final String CLAIM_ROLE = "role";

    /**
     * 生成JWT令牌
     * @param userDetails 用户详细信息
     * @return 生成的JWT字符串
     */
    public String generateJwtToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, userDetails.getId());
        claims.put(CLAIM_ROLE, userDetails.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * 解析JWT令牌获取用户ID
     * @param token JWT令牌
     * @return 用户ID
     */
    public Long getUserIdFromJwtToken(String token) {
        return (Long) extractClaim(token, Claims::getSubject);
    }

    /**
     * 解析JWT令牌获取用户角色
     * @param token JWT令牌
     * @return 用户角色
     */
    public String getUserRoleFromJwtToken(String token) {
        return (String) extractClaim(token, Claims::get);
    }

    /**
     * 通用声明提取方法
     * @param token     JWT令牌
     * @param claimsGetter 声明提取函数
     * @return 声明值
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsGetter) {
        return claimsGetter.apply(Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody());
    }

    /**
     * 验证JWT令牌有效性
     * @param token JWT令牌
     * @return 令牌是否有效
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.error("JWT验证失败: {}", e.getMessage());
            return false;
        }
    }
}