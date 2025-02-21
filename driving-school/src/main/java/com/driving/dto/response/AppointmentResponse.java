
package com.driving.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.driving.entity.Appointment;
import java.time.LocalDateTime;

public class AppointmentResponse {

    private Long id;

    private Long coachId;

    private Long studentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private String status;

    private String notes;

    private long durationMinutes; // 计算好的时长（分钟）

    // 构造函数
    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.coachId = appointment.getCoach().getId();
        this.studentId = appointment.getStudent().getId();
        this.startTime = appointment.getStartTime();
        this.endTime = appointment.getEndTime();
        this.status = appointment.getStatus();
        this.notes = appointment.getNotes();
        this.durationMinutes = appointment.calculateDuration();
    }

    // Getters
    public Long getId() {
        return id;
    }

    // ...其他字段的getter
}