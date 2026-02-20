package com.student.student_library_management.controller;

import com.student.student_library_management.requestDTO.AdminLoginDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.full-name}")
    private String adminFullName;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminLoginDto loginDto) {

        // Check credentials
        if (adminUsername.equals(loginDto.getUsername()) &&
                adminPassword.equals(loginDto.getPassword())) {

            // Generate simple token
            String token = UUID.randomUUID().toString();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("username", adminUsername);
            response.put("email", adminEmail);
            response.put("fullName", adminFullName);
            response.put("role", "ADMIN");

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid username or password"));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        Map<String, Object> profile = new HashMap<>();
        profile.put("username", adminUsername);
        profile.put("email", adminEmail);
        profile.put("fullName", adminFullName);
        profile.put("role", "ADMIN");

        return ResponseEntity.ok(profile);
    }
}