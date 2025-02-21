
package com.driving.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class AppointmentRequest {

    @NotNull(message = "课程ID不能为空")
    private Long coachId;

    @NotNull(message = "学生ID不能为空")
    private Long studentId;

    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Size(min = 1, max = 200, message = "备注长度必须在1-200字符之间")
    private String notes;

    // Getters & Setters
    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    // ...其他字段的getter/setter
}