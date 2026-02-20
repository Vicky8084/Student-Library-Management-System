package com.student.student_library_management.model;

import com.student.student_library_management.enums.LibrarianStatus;
import com.student.student_library_management.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "librarian")
@EntityListeners(AuditingEntityListener.class)
public class Librarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;  // Good name for password hash

    @Column(name = "employee_id", nullable = false, unique = true)
    private String empId;

    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.LIBRARIAN;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LibrarianStatus status = LibrarianStatus.INACTIVE;

    @CreatedDate
    @Column(name = "joining_date", nullable = false, updatable = false)
    private LocalDate joiningDate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

}