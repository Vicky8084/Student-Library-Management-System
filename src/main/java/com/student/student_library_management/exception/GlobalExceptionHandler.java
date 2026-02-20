package com.student.student_library_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice //IMPORTANT: Ye annotation Spring ko batata hai ki ye global handler hai
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateEmailException.class) //Ye batata hai ki ye method StudentNotFoundException handle karega
    public ResponseEntity<String> handleDuplicateEmail(DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateRegistrationNumberException.class)
    public ResponseEntity<String> handleDuplicateRegistrationNumber(DuplicateRegistrationNumberException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(LibrarianNotFoundException.class)
    public ResponseEntity<String> handleLibrarianNotFound(LibrarianNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
