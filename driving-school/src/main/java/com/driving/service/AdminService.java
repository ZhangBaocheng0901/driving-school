// src/main/java/com/driving/service/AdminService.java
package com.driving.service;

import com.driving.entity.Admin;
import com.driving.entity.Student;
import com.driving.repository.AdminRepository;
import com.driving.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private StudentRepository studentRepo;

    public List<Student> getAllStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    public void deleteStudent(Long studentId) {
        studentRepo.deleteById(studentId);
    }

    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }
}