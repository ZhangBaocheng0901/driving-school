
package com.driving.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotNull(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20字符之间")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 40, message = "密码长度必须在6-40字符之间")
    private String password;

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPassword() {
        return null;
    }

    // ...其他字段的getter/setter
}