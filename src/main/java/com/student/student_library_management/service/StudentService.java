package com.student.student_library_management.service;
import com.student.student_library_management.enums.CardStatus;
import com.student.student_library_management.exception.DuplicateEmailException;
import com.student.student_library_management.exception.DuplicateRegistrationNumberException;
import com.student.student_library_management.exception.StudentNotFoundException;
import com.student.student_library_management.model.Card;
import com.student.student_library_management.model.Student;
import com.student.student_library_management.repository.LibrarianRepository;
import com.student.student_library_management.repository.StudentRepository;
import com.student.student_library_management.requestDTO.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.student.student_library_management.converter.StudentConverter.convertRequestDtoIntoStudent;

@Service
public class StudentService {
    CardService cardService;
    StudentRepository studentRepository;
    LibrarianRepository librarianRepository;
    PasswordEncoder passwordEncoder;
    EmailNotificationService emailNotificationService;
    @Autowired
    public StudentService(CardService cardService,
                          LibrarianRepository librarianRepository,
                          PasswordEncoder passwordEncoder,
                          StudentRepository studentRepository,
                          EmailNotificationService emailNotificationService){
        this.cardService=cardService;
        this.studentRepository=studentRepository;
        this.librarianRepository=librarianRepository;
        this.passwordEncoder=passwordEncoder;
        this.emailNotificationService=emailNotificationService;
    }

    public ResponseEntity saveStudent(StudentRequestDto studentRequestDto){

        String email=studentRequestDto.getEmail();

        //Checking Both tables for Duplicate email
        if (studentRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already exists a Student: " +email);
        }
        if (librarianRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already exists a Librarian: " + email);
        }

        //Checking for Duplicate Registration Number
        if(studentRepository.existsByRegistrationNumber(studentRequestDto.getRegistrationNumber())){
            throw new DuplicateRegistrationNumberException("Registration Number already exists: "+studentRequestDto.getRegistrationNumber());
        }

        //Converting Dto to Entity Table
        Student student=convertRequestDtoIntoStudent(studentRequestDto);

        //Password Encrypting
        String rawPassword=studentRequestDto.getPassword();
        String cryptedPassword=passwordEncoder.encode(rawPassword);
        student.setPassword(cryptedPassword);

        // Create and setup card
        Card card = new Card();
        card.setStatus(CardStatus.ACTIVE);
        String cardNumber = cardService.generateCardNumber();
        card.setCardNumber(cardNumber);
        card.setStudent(student);
        student.setCard(card);

        // Save to database
        Student response = studentRepository.save(student);
        emailNotificationService.sendStudentRegistrationNotification(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    public ResponseEntity<Student> findStudentById(int id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not found with id :- "+id));
        return new ResponseEntity(student,HttpStatus.OK);
    }
    public ResponseEntity findAllStudent(){
        List<Student> studentList =studentRepository.findAll();
        return new ResponseEntity(studentList,HttpStatus.OK);
    }

    private Student helperToFindStudentByID(int id){
        Optional<Student> student= studentRepository.findById(id);
        if (student.isPresent()){
            return student.get();
        }
        else return null;
    }

    public ResponseEntity updateStudentById(int id, StudentRequestDto studentRequestDto){
        Student student=helperToFindStudentByID(id);
        if(student!=null){
            student.setName(studentRequestDto.getName());
            student.setRegistrationNumber(studentRequestDto.getRegistrationNumber());
            student.setEmail(studentRequestDto.getEmail());
            student.setMobileNumber(studentRequestDto.getMobileNumber());
            student.setDateOfBirth(studentRequestDto.getDateOfBirth());
            student.setCourse(studentRequestDto.getCourse());
            student.setBloodGroup(studentRequestDto.getBloodGroup());
            student.setSemester(studentRequestDto.getSemester());
            student.setGender(studentRequestDto.getGender());
            student=studentRepository.save(student);

            return new ResponseEntity(student,HttpStatus.OK);
        }
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity deleteStudentById(int id){
        Student student=helperToFindStudentByID(id);
        if(student!=null) {
            studentRepository.delete(student);
            return new ResponseEntity(HttpStatus.OK);
        }
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
