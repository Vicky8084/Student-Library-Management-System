package com.student.student_library_management.repository;

import com.student.student_library_management.model.Librarian;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
   boolean existsByEmail(String email);
}
