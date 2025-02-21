-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS driving_school;
USE driving_school;

-- 创建管理员表
CREATE TABLE admin (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100),
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建教练表
CREATE TABLE coach (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       specialty VARCHAR(100),
                       contact_number VARCHAR(20),
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ... 其他表结构保持不变 ...

-- 创建车辆表
CREATE TABLE vehicle (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         license_plate VARCHAR(50) UNIQUE NOT NULL,
                         status ENUM('AVAILABLE', 'UNDER_MAINTENANCE', 'IN_USE') DEFAULT 'AVAILABLE',
                         last_maintenance_date DATETIME,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);