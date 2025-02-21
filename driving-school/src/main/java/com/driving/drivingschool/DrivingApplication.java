
package com.driving;

import com.driving.config.GlobalExceptionHandler;
import com.driving.dto.response.JwtResponse;
import com.driving.service.AdminService;
import com.driving.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DrivingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrivingApplication.class, args);
    }

    // 数据库初始化（运行时自动执行 data.sql）
    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            System.out.println("Initializing database with sample data...");
            // 可在此处手动插入测试数据，或通过 SQL 文件自动导入
        };
    }

    // 密码编码器（用于用户注册和登录）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // 示例：注入服务并执行初始化逻辑
    @Bean
    public void initUserService(AuthService authService, AdminService adminService) {
        // 创建测试用户（生产环境中应删除）
        authService.registerUser("admin", "password123", "ADMIN");
        authService.registerUser("coach1", "password123", "COACH");
        authService.registerUser("student1", "password123", "STUDENT");
    }
}