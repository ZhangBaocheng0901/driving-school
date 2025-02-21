
package com.driving.controller;

import com.driving.dto.request.AppointmentRequest;
import com.driving.dto.response.AppointmentResponse;
import com.driving.entity.Appointment;
import com.driving.entity.Student;
import com.driving.security.UserDetailsImpl;
import com.driving.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 获取学员信息
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // 创建预约
    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = studentService.createAppointment(request);
        return ResponseEntity.ok(new AppointmentResponse(appointment));
    }

    // 查询我的预约
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAppointments() {
        return ResponseEntity.ok(studentService.getAppointmentsByUserId(getCurrentUserId()));
    }

    private Long getCurrentUserId() {
        // 通过JWT解析获取当前用户ID（需从SecurityContext中获取）
        return ((UserDetailsImpl) org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getId();
    }
}