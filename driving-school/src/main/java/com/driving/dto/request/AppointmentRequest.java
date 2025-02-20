// src/main/java/com/driving/dto/request/AppointmentRequest.java
package com.driving.dto.request;

@Data
public class AppointmentRequest {
    @NotNull(message = "教练ID不能为空")
    private Long coachId;

    @Future(message = "预约时间必须是将来的时间")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    @Min(value = 1, message = "课时至少1小时")
    @Max(value = 4, message = "单次最多预约4小时")
    private int durationHours;
}