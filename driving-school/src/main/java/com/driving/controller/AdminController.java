
package com.driving.controller;

import com.driving.dto.response.StatsResponse;
import com.driving.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminController
 *
 * @since 2025-02-22
 */

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private AdminService adminService;

    // 获取学员每日练习时长统计
    /**
     * getStudentDailyPractice
     *
     * @return ResponseEntity<StatsResponse>
     */
    @GetMapping("/student-practice")
    public ResponseEntity<StatsResponse> getStudentDailyPractice() {
        return ResponseEntity.ok(adminService.getStudentDailyPractice());
    }

    // 获取教练每日教学时长统计
    /**
     * getCoachDailyTeaching
     *
     * @return ResponseEntity<StatsResponse>
     */
    @GetMapping("/coach-teaching")
    public ResponseEntity<StatsResponse> getCoachDailyTeaching() {
        return ResponseEntity.ok((StatsResponse) adminService.getCoachDailyTeaching());
    }
}