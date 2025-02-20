// src/main/java/com/driving/dto/response/JwtResponse.java
package com.driving.dto.response;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private String userRole;

    public JwtResponse(String token) {
        this.token = token;
    }
}