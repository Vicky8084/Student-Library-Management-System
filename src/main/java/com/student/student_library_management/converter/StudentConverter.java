package com.student.student_library_management.converter;

import com.student.student_library_management.model.Student;
import com.student.student_library_management.requestDTO.StudentRequestDto;

public class StudentConverter {
    public static Student convertRequestDtoIntoStudent(StudentRequestDto studentRequestDto){
        Student student=new Student();
        student.setName(studentRequestDto.getName());
        student.setRegistrationNumber(studentRequestDto.getRegistrationNumber());
        student.setEmail(studentRequestDto.getEmail());
        student.setMobileNumber(studentRequestDto.getMobileNumber());
        student.setDateOfBirth(studentRequestDto.getDateOfBirth());
        student.setCourse(studentRequestDto.getCourse());
        student.setBloodGroup(studentRequestDto.getBloodGroup());
        student.setSemester(studentRequestDto.getSemester());
        student.setGender(studentRequestDto.getGender());
        student.setRole(studentRequestDto.getRole());
        student.setPassword(studentRequestDto.getPassword());
        return student;
    }

}

/*
    private String name;
    private String registrationNumber;
    private String email;
    private String mobileNumber;
    private LocalDate dateOfBirth;
    private String course;
    private BloodGroup bloodGroup;
    private Semester semester;
    private Gender gender;
 */