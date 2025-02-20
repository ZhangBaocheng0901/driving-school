// src/main/java/com/driving/config/SecurityConfig.java
package com.driving.config;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 安全过滤器链配置
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF和开启跨域
                .cors().and().csrf().disable()

                // 授权配置
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()       // 认证接口公开
                .antMatchers("/api/student/**").hasRole("STUDENT")
                .antMatchers("/api/coach/**").hasRole("COACH")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()

                // 会话管理（无状态）
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // 添加JWT过滤器
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // 用户详情服务
                .userDetailsService(userDetailsService);

        return http.build();
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}