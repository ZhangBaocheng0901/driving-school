// src/main/java/com/driving/dto/request/TimeSlotRequest.java
package com.driving.dto.request;

@Data
public class TimeSlotRequest {
    @FutureOrPresent(message = "开始时间不能是过去时间")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    @AssertTrue(message = "结束时间必须晚于开始时间")
    private boolean isValidTimeRange() {
        return endTime.isAfter(startTime);
    }
}