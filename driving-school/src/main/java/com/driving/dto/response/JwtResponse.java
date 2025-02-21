
package com.driving.dto.response;

public class JwtResponse {

    private String accessToken;

    private String refreshToken;

    private Long expiresIn;

    public JwtResponse(String accessToken, String refreshToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    // Getters
    public String getAccessToken() {
        return accessToken;
    }

    // ...其他字段的getter
}