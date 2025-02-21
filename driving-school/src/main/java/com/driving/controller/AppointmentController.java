
package com.driving.controller;

import com.driving.dto.request.AppointmentRequest;
import com.driving.dto.response.AppointmentResponse;
import com.driving.entity.Appointment;
import com.driving.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@PreAuthorize("hasAnyRole('ADMIN', 'COACH', 'STUDENT')")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // 创建预约
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentService.createAppointment(request);
        return ResponseEntity.ok(new AppointmentResponse(appointment));
    }

    // 查询个人所有预约
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByUserId(userId));
    }

    // 获取所有预约（管理员）
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // 更新预约状态
    @PutMapping("/{appointmentId}/status")
    @PreAuthorize("hasRole('ADMIN', 'COACH')")
    public ResponseEntity<Void> updateAppointmentStatus(@PathVariable Long appointmentId, @RequestParam String status) {
        appointmentService.updateAppointmentStatus(appointmentId, status);
        return ResponseEntity.ok().build();
    }
}