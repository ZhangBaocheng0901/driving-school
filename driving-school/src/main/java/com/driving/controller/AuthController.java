
package com.driving.controller;

import com.driving.dto.request.LoginRequest;
import com.driving.dto.response.JwtResponse;
import com.driving.security.JwtUtils;
import com.driving.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))
                .setAuthenticated(true);

        UserDetails userDetails = authService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    // 用户注册（需管理员审核）
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody LoginRequest request) {
        authService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().build();
    }
}