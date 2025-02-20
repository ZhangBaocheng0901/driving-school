// src/main/java/com/driving/controller/CoachController.java
package com.driving.controller;

@RestController
@RequestMapping("/api/coach")
@PreAuthorize("hasRole('COACH')")
public class CoachController {
    @Autowired
    private CoachService coachService;

    /**
     * 获取教练的待处理预约
     */
    @GetMapping("/pending-appointments")
    public ResponseEntity<List<AppointmentResponse>> getPendingAppointments(
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(coachService.getPendingAppointments(token));
    }

    /**
     * 确认预约
     */
    @PutMapping("/confirm-appointment/{id}")
    public ResponseEntity<AppointmentResponse> confirmAppointment(
            @PathVariable Long id) {
        return ResponseEntity.ok(coachService.confirmAppointment(id));
    }

    /**
     * 设置可用时间段
     */
    @PostMapping("/availability")
    public ResponseEntity<Void> setAvailability(
            @RequestBody List<TimeSlotRequest> slots,
            @RequestHeader("Authorization") String token) {
        coachService.setAvailability(token, slots);
        return ResponseEntity.ok().build();
    }
}