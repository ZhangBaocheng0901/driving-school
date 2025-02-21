
package com.driving.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.driving.entity.Admin;
import com.driving.entity.Coach;
import com.driving.entity.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration; // 24小时（毫秒）

    // 生成JWT令牌
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities());

        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.from(LocalDateTime.now())))
                .setExpiration(new Date(LocalDateTime.now().plusHours(1).toInstant())) // 1小时有效期（示例）
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 解析JWT令牌
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromJwt(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 获取用户名
    public String getUsernameFromJwt(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
    }

    // 检查令牌是否过期
    private Boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getExpiration()
                .before(Date.from(Instant.from(LocalDateTime.now())));
    }

    // 获取用户角色
    public List<String> getUserRoles(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().get("roles",
                List.class);
    }
}