// src/main/java/com/driving/service/AppointmentService.java
package com.driving.service;

import com.driving.dto.request.AppointmentRequest;
import com.driving.dto.response.AppointmentResponse;
import com.driving.entity.Appointment;
import com.driving.entity.Student;
import com.driving.entity.Coach;
import com.driving.repository.AppointmentRepository;
import com.driving.repository.StudentRepository;
import com.driving.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CoachRepository coachRepo;

    @Transactional
    public AppointmentResponse createAppointment(AppointmentRequest request, String token) {
        // 获取当前用户身份（学员）
        Long studentId = getUserIdFromJwtToken(token);
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new CustomException("学员不存在"));

        Coach coach = coachRepo.findById(request.getCoachId())
                .orElseThrow(() -> new CustomException("教练不存在"));

        Appointment appointment = new Appointment();
        appointment.setStudent(student);
        appointment.setCoach(coach);
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setStatus(Appointment.Status.PENDING);

        Appointment saved = appointmentRepo.save(appointment);

        return AppointmentResponse.builder()
                .id(saved.getId())
                .status(saved.getStatus())
                .startTime(saved.getStartTime())
                .endTime(saved.getEndTime())
                .student(StudentDTO.fromEntity(saved.getStudent()))
                .coach(CoachDTO.fromEntity(saved.getCoach()))
                .build();
    }

    @Transactional
    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new CustomException("预约不存在"));
        appointment.setStatus(Appointment.Status.CANCELLED);
        appointmentRepo.save(appointment);
    }

    public List<AppointmentResponse> getUserAppointments(String token) {
        Long userId = getUserIdFromJwtToken(token);
        List<Appointment> appointments = appointmentRepo.findByStudentId(userId);

        return appointments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private Long getUserIdFromJwtToken(String token) {
        return JwtUtils.getUserIdFromJwtToken(token);
    }

    private AppointmentResponse convertToResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .status(appointment.getStatus())
                .startTime(appointment.getStartTime())
                .endTime(appointment.getEndTime())
                .student(StudentDTO.fromEntity(appointment.getStudent()))
                .coach(CoachDTO.fromEntity(appointment.getCoach()))
                .build();
    }
}