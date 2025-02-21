
package com.driving.service;

import com.driving.dto.request.LoginRequest;
import com.driving.entity.Admin;
import com.driving.entity.Coach;
import com.driving.entity.Student;
import com.driving.exception.InvalidRequestException;
import com.driving.exception.ResourceNotFoundException;
import com.driving.repository.AdminRepository;
import com.driving.repository.CoachRepository;
import com.driving.repository.StudentRepository;
import com.driving.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 用户注册（需管理员审核）
    @Transactional
    public void registerUser(String username, String password, String role) {
        if ("ADMIN".equals(role)) {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(password));
            adminRepository.save(admin);
        } else if ("COACH".equals(role)) {
            Coach coach = new Coach();
            coach.setUsername(username);
            coach.setPassword(passwordEncoder.encode(password));
            coachRepository.save(coach);
        } else if ("STUDENT".equals(role)) {
            Student student = new Student();
            student.setUsername(username);
            student.setPassword(passwordEncoder.encode(password));
            studentRepository.save(student);
        } else {
            throw new InvalidRequestException("Invalid user role");
        }
    }

    // 加载用户详细信息（用于JWT验证）
    public UserDetailsImpl loadUserByUsername(String username) {
        Optional<Admin> adminOptional = Optional.ofNullable(adminRepository.findByUsername(username));
        if (adminOptional.isPresent()) {
            return new UserDetailsImpl(adminOptional.get());
        }

        Optional<Coach> coachOptional = coachRepository.findByUsername(username);
        if (coachOptional.isPresent()) {
            return new UserDetailsImpl(coachOptional.get());
        }

        Optional<Student> studentOptional = studentRepository.findByUsername(username);
        if (studentOptional.isPresent()) {
            return new UserDetailsImpl(studentOptional.get());
        }

        throw new ResourceNotFoundException("User not found");
    }

    public void registerUser(String username, Object password) {
    }
}