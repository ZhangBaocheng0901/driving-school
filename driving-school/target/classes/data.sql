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

-- 创建学员表
CREATE TABLE student (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         student_id VARCHAR(20) UNIQUE NOT NULL,
                         contact_number VARCHAR(20),
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建预约表
CREATE TABLE appointment (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             coach_id BIGINT,
                             student_id BIGINT,
                             start_time DATETIME NOT NULL,
                             end_time DATETIME NOT NULL,
                             status ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED') DEFAULT 'SCHEDULED',
                             notes TEXT,
                             vehicle_id BIGINT,
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                             FOREIGN KEY (coach_id) REFERENCES coach(id),
                             FOREIGN KEY (student_id) REFERENCES student(id),
                             FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);

-- 创建时间段表
CREATE TABLE time_slot (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           coach_id BIGINT,
                           start_time DATETIME NOT NULL,
                           end_time DATETIME NOT NULL,
                           description TEXT,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                           FOREIGN KEY (coach_id) REFERENCES coach(id)
);

-- 创建车辆表
CREATE TABLE vehicle (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         license_plate VARCHAR(50) UNIQUE NOT NULL,
                         status ENUM('AVAILABLE', 'UNDER_MAINTENANCE', 'IN_USE') DEFAULT 'AVAILABLE',
                         last_maintenance_date DATETIME,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 插入测试数据
INSERT INTO admin (username, password, email)
VALUES
    ('admin', '$2a$12$eGq5FgOvPqZq4tT9lKdpuJ2xGmYrL3vBqZq4tT9lKdpuJ', 'admin@example.com');

INSERT INTO coach (name, specialty, contact_number)
VALUES
    ('张教练', '驾驶培训', '13800138000');

INSERT INTO student (name, student_id, contact_number)
VALUES
    ('李学员', 'S2025001', '15900159000');