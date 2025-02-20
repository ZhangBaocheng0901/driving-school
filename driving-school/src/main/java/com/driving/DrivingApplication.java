// src/main/java/com/driving/DrivingApplication.java
package com.driving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 * 项目入口，负责初始化应用程序上下文
 */
@SpringBootApplication
public class DrivingApplication {

    /**
     * 主方法 - 应用程序启动入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 启动Spring Boot应用
        SpringApplication.run(DrivingApplication.class, args);

        // 打印启动成功日志（实际生产环境建议使用Logger）
        System.out.println("=====================================");
        System.out.println("驾校管理系统启动成功！");
        System.out.println("访问地址: http://localhost:8080");
        System.out.println("=====================================");
    }
}