package com.student.student_library_management.repository;

import com.student.student_library_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByEmail(String email);
    boolean existsByRegistrationNumber(String registrationNumber);
}
