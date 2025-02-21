
package com.driving.controller;

import com.driving.dto.request.TimeSlotRequest;
import com.driving.entity.Appointment;
import com.driving.entity.Coach;
import com.driving.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@PreAuthorize("hasRole('ADMIN', 'COACH')")
public class CoachController {

    @Autowired
    private CoachService coachService;

    // 获取教练信息
    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.getCoachById(id));
    }

    // 创建时间段
    @PostMapping("/timeslots")
    public ResponseEntity<Void> createTimeSlot(@RequestBody TimeSlotRequest request) {
        coachService.createTimeSlot(request.getCoachId(), request.getTimeSlot());
        return ResponseEntity.ok().build();
    }

    // 查询空闲时间段
    @GetMapping("/free-timeslots/{coachId}")
    public ResponseEntity<List<Appointment>> getFreeTimeslots(@PathVariable Long coachId) {
        return ResponseEntity.ok(coachService.getFreeTimeslots(coachId));
    }
}