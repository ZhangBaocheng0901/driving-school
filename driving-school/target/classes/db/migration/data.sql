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