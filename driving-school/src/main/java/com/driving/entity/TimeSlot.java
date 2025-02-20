// src/main/java/com/driving/entity/TimeSlot.java
package com.driving.entity;

@Embeddable
@Data
public class TimeSlot {
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private boolean available;
}