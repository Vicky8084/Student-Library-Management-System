package com.student.student_library_management.Controller;

import com.student.student_library_management.requestDTO.StudentRequestDto;
import com.student.student_library_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("student/api")
public class StudentController {
    StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("/save")
    public ResponseEntity saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        ResponseEntity response=studentService.saveStudent(studentRequestDto);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity findStudentById(@PathVariable int id){
        ResponseEntity response=studentService.findStudentById(id);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/GetAllStudent")
    public ResponseEntity findAllStudent(){
        ResponseEntity studentList= studentService.findAllStudent();
        return new ResponseEntity(studentList,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudentById(@PathVariable int id,@RequestBody StudentRequestDto studentRequestDto){
        ResponseEntity response=studentService.updateStudentById(id,studentRequestDto);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentById(@PathVariable int id){
        ResponseEntity response=studentService.deleteStudentById(id);
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
