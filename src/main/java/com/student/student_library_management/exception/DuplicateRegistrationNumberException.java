package com.student.student_library_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRegistrationNumberException extends RuntimeException {
    public DuplicateRegistrationNumberException(String message) {
        super(message);
    }
}
