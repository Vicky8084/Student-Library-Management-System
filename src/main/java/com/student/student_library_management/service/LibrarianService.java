package com.student.student_library_management.service;

import com.student.student_library_management.exception.DuplicateEmailException;
import com.student.student_library_management.exception.LibrarianNotFoundException;
import com.student.student_library_management.model.Librarian;
import com.student.student_library_management.repository.LibrarianRepository;
import com.student.student_library_management.repository.StudentRepository;
import com.student.student_library_management.requestDTO.LibrarianRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static com.student.student_library_management.converter.LibrarianConverter.convertLibrarianRequestDtoIntoLibrarian;

@Service
public class LibrarianService {

    public static final long BASE_SEQUENCE=2342;
    PasswordEncoder passwordEncoder;
    LibrarianRepository librarianRepository;
    StudentRepository studentRepository;
    @Autowired
    public LibrarianService(LibrarianRepository librarianRepository,
                            PasswordEncoder passwordEncoder,
                            StudentRepository studentRepository){
        this.librarianRepository=librarianRepository;
        this.passwordEncoder=passwordEncoder;
        this.studentRepository=studentRepository;

    }

    public String generateEmployeeId(){
        String prefix="LIB";
        String year=String.valueOf(Year.now().getValue());
        long currentCount=librarianRepository.count();
        long nextSequence=BASE_SEQUENCE+currentCount;
        String sequence=String.format("%04d",nextSequence);
        return prefix+year+sequence;
    }

    public ResponseEntity saveLibrarian(LibrarianRequestDto librarianRequestDto){

        String email=librarianRequestDto.getEmail();
        if(librarianRepository.existsByEmail(email)){
            throw new DuplicateEmailException("Email Already exists as a Librarian: "+email);
        }
        if(studentRepository.existsByEmail(email)){
            throw new DuplicateEmailException("Email Already exists as a Student: "+email);
        }

        Librarian librarian=convertLibrarianRequestDtoIntoLibrarian(librarianRequestDto);

        //............Password Encoded..............
        String rawPassword= librarianRequestDto.getPassword();;
        String encryptedPassword=passwordEncoder.encode(rawPassword);
        librarian.setPassword(encryptedPassword);

        //...............Employee Id Generated.............
        String empId=generateEmployeeId();
        librarian.setEmpId(empId);
        librarian =librarianRepository.save(librarian);
        return new ResponseEntity(librarian, HttpStatus.CREATED);
    }

    public ResponseEntity findLibrarianById(int id){
        Librarian librarian=librarianRepository.findById(id)
                .orElseThrow(()->new LibrarianNotFoundException("Librarian not found with id: "+id));
        return new ResponseEntity(librarian,HttpStatus.OK);

    }

    public ResponseEntity findAllLibrarian(){
        List<Librarian> librarians = librarianRepository.findAll();
        return new ResponseEntity(librarians,HttpStatus.OK);
    }

    public Librarian helperToFindLibrarian(int id){
        Optional<Librarian> librarian=librarianRepository.findById(id);
        if(librarian!=null){
            return librarian.get();
        }
        return null;
    }

    public ResponseEntity updateLibrarianById(int id, LibrarianRequestDto librarianRequestDto){
        Librarian librarian=helperToFindLibrarian(id);
        if(librarian!=null){
            librarian.setName(librarianRequestDto.getName());
            librarian.setEmail(librarianRequestDto.getEmail());
            librarian.setPassword(librarianRequestDto.getPassword());
            librarian.setMobileNumber(librarianRequestDto.getMobileNumber());
            librarianRepository.save(librarian);
            return new ResponseEntity(librarian,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity deleteLibrarianById(int id){
        Librarian librarian=helperToFindLibrarian(id);
        if(librarian!=null){
            librarianRepository.delete(librarian);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
