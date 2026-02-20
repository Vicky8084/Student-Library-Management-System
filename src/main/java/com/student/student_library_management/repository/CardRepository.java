package com.student.student_library_management.repository;

import com.student.student_library_management.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
