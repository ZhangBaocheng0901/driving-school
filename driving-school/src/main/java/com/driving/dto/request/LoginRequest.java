// src/main/java/com/driving/dto/request/LoginRequest.java
package com.driving.dto.request;

@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度4-20个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 40, message = "密码长度6-40个字符")
    private String password;

    @NotBlank(message = "用户类型不能为空")
    @Pattern(regexp = "student|coach|admin", message = "无效的用户类型")
    private String userType;
}