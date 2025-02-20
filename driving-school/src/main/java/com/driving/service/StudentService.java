// src/main/java/com/driving/service/StudentService.java
package com.driving.service;

import com.driving.entity.Student;
import com.driving.entity.Appointment;
import com.driving.exception.ResourceNotFoundException;
import com.driving.repository.StudentRepository;
import com.driving.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 查询所有学员
    public Page<Student> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // 根据ID查询学员
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    // 创建/更新学员
    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // 删除学员
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // 查询学员的所有预约
    public List<Appointment> getAppointmentsByStudent(Long studentId) {
        return appointmentRepository.findByStudentId(studentId);
    }

    // 创建预约（学员视角）
    @Transactional
    public Appointment createAppointment(Appointment appointment, Long studentId) {
        Student student = findStudentById(studentId);
        appointment.setStudent(student);
        return appointmentRepository.save(appointment);
    }

    // 更新学员状态（启用/禁用）
    @Transactional
    public Student updateStudentStatus(Long id, boolean active) {
        Student student = findStudentById(id);
        student.setActive(active);
        return studentRepository.save(student);
    }
}