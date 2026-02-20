package com.student.student_library_management.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminLoginDto {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}