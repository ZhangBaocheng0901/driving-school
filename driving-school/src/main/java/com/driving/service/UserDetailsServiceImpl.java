// src/main/java/com/driving/service/UserDetailsServiceImpl.java
package com.driving.service;

import com.driving.security.UserDetailsImpl;
import com.driving.entity.Student;
import com.driving.entity.Coach;
import com.driving.entity.Admin;
import com.driving.repository.StudentRepository;
import com.driving.repository.CoachRepository;
import com.driving.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CoachRepository coachRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Student student = studentRepo.findByUsername(username);
        if (student != null) {
            return new UserDetailsImpl(student);
        }

        Coach coach = coachRepo.findByUsername(username);
        if (coach != null) {
            return new UserDetailsImpl(coach);
        }

        Admin admin = adminRepo.findByUsername(username);
        if (admin != null) {
            return new UserDetailsImpl(admin);
        }

        throw new UsernameNotFoundException("用户不存在");
    }
}