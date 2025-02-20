// src/main/java/com/driving/service/AuthService.java
package com.driving.service;

import com.driving.dto.request.LoginRequest;
import com.driving.entity.Admin;
import com.driving.entity.Coach;
import com.driving.entity.Student;
import com.driving.repository.AdminRepository;
import com.driving.repository.CoachRepository;
import com.driving.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CoachRepository coachRepo;

    @Autowired
    private AdminRepository adminRepo;

    public void registerUser(LoginRequest loginRequest) {
        String userType = loginRequest.getUserType();
        String username = loginRequest.getUsername();

        switch (userType) {
            case "student":
                Student student = new Student();
                student.setUsername(username);
                student.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                studentRepo.save(student);
                break;
            case "coach":
                Coach coach = new Coach();
                coach.setUsername(username);
                coach.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                coachRepo.save(coach);
                break;
            case "admin":
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
                adminRepo.save(admin);
                break;
        }
    }

    public UserDetails loadUserByUsername(String username) {
        if (studentRepo.existsByUsername(username)) {
            return studentRepo.findById(username).orElse(null);
        } else if (coachRepo.existsByUsername(username)) {
            return coachRepo.findById(username).orElse(null);
        } else if (adminRepo.existsByUsername(username)) {
            return adminRepo.findById(username).orElse(null);
        }
        return null;
    }
}