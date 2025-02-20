-- src/main/resources/data.sql
-- 初始化管理员
INSERT INTO admin (username, password) VALUES
    ('admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

-- 初始化教练
INSERT INTO coach (username, password, name, specialty) VALUES
                                                            ('coach_li', '$2a$10$...', '李教练', '科目二'),
                                                            ('coach_wang', '$2a$10$...', '王教练', '科目三');

-- 初始化学员
INSERT INTO student (username, password, real_name, phone) VALUES
                                                               ('student_zhang', '$2a$10$...', '张三', '13800138000'),
                                                               ('student_li', '$2a$10$...', '李四', '13900139000');