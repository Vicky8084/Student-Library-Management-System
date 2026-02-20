package com.student.student_library_management.requestDTO;

import com.student.student_library_management.enums.BloodGroup;
import com.student.student_library_management.enums.Gender;
import com.student.student_library_management.enums.Semester;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Registration Number is Required")
    private String registrationNumber;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Format")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @NotNull(message ="Date of Birth is Required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Course is Required")
    private String course;

    @NotNull(message = "Blood Group is Required")
    private BloodGroup bloodGroup;

    @NotNull(message = "Semester is Required")
    private Semester semester;

    @NotNull(message = "Gender is Required")
    private Gender gender;

}
