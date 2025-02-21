
package com.driving.service;

import com.driving.dto.request.AppointmentRequest;
import com.driving.dto.response.AppointmentResponse;
import com.driving.entity.Appointment;
import com.driving.entity.Coach;
import com.driving.entity.Student;
import com.driving.exception.ConflictException;
import com.driving.exception.ResourceNotFoundException;
import com.driving.repository.AppointmentRepository;
import com.driving.repository.CoachRepository;
import com.driving.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private StudentRepository studentRepository;

    // 创建预约
    @Transactional
    public Appointment createAppointment(AppointmentRequest request) {
        Coach coach = coachRepository.findById(request.getCoachId())
                .orElseThrow(() -> new ResourceNotFoundException("Coach not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Appointment appointment = new Appointment();
        appointment.setCoach(coach);
        appointment.setStudent(student);
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setStatus("SCHEDULED");
        appointment.setNotes(request.getNotes());

        return appointmentRepository.save(appointment);
    }

    // 查询用户的所有预约
    @Transactional(readOnly = true)
    public List<AppointmentResponse> getAppointmentsByUserId(Long userId) {
        List<Appointment> appointments = appointmentRepository.findByStudentIdOrCoachId(userId, userId);

        return appointments.stream().map(AppointmentResponse::new).collect(Collectors.toList());
    }

    // 更新预约状态
    @Transactional
    public void updateAppointmentStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        // 业务逻辑校验：例如不能从 COMPLETED 状态转回 SCHEDULED
        if ("COMPLETED".equals(appointment.getStatus()) && "SCHEDULED".equals(status)) {
            throw new ConflictException("Invalid status transition");
        }

        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    public List<AppointmentResponse> getAllAppointments() {
    }
}