// src/main/java/com/driving/dto/response/AppointmentResponse.java
package com.driving.dto.response;

@Data
@Builder
public class AppointmentResponse {
    private Long id;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @JsonIgnoreProperties({"password", "appointments"})
    private StudentDTO student;

    @JsonIgnoreProperties({"password", "availableSlots"})
    private CoachDTO coach;

    // 学生DTO
    @Data
    public static class StudentDTO {
        private Long id;
        private String realName;
        private String phone;
    }

    // 教练DTO
    @Data
    public static class CoachDTO {
        private Long id;
        private String name;
        private String specialty;
    }
}