// src/main/java/com/driving/config/CorsConfig.java
package com.driving.config;

@Configuration
public class CorsConfig {

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 允许所有域名访问（生产环境应指定具体域名）
        config.addAllowedOrigin("*");
        // 允许携带凭证（如cookies）
        config.setAllowCredentials(true);
        // 允许所有请求方法
        config.addAllowedMethod("*");
        // 允许所有请求头
        config.addAllowedHeader("*");
        // 预检请求缓存时间（单位：秒）
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}