// src/main/java/com/driving/controller/AppointmentController.java
package com.driving.controller;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    /**
     * 创建新预约（学员）
     */
    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody AppointmentRequest request,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.createAppointment(request, token));
    }

    /**
     * 取消预约（学员/管理员）
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 获取当前用户预约列表
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'COACH')")
    public ResponseEntity<List<AppointmentResponse>> getUserAppointments(
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(appointmentService.getUserAppointments(token));
    }
}