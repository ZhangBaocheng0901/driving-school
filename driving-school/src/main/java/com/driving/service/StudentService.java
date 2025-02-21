
package com.driving.service;

import com.driving.dto.request.AppointmentRequest;
import com.driving.entity.Appointment;
import com.driving.entity.Student;
import com.driving.repository.AppointmentRepository;
import com.driving.repository.StudentRepository;
import com.driving.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 获取学员信息
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    // 创建预约
    @Transactional
    public Appointment createAppointment(AppointmentRequest request) {
        Student student = getStudentById(request.getStudentId());

        Appointment appointment = new Appointment();
        appointment.setStudent(student);
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setStatus("SCHEDULED");
        appointment.setNotes(request.getNotes());

        return appointmentRepository.save(appointment);
    }

    // 查询我的预约
    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appointmentRepository.findByStudentId(userId);
    }
}