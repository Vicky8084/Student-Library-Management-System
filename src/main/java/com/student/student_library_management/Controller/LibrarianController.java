package com.student.student_library_management.Controller;

import com.student.student_library_management.requestDTO.LibrarianRequestDto;
import com.student.student_library_management.service.LibrarianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian/api")
public class LibrarianController {

    LibrarianService librarianService;
    public LibrarianController(LibrarianService librarianService){
        this.librarianService=librarianService;
    }

    @PostMapping("/save")
    public ResponseEntity saveLibrarian(@RequestBody LibrarianRequestDto librarianRequestDto){
        ResponseEntity response=librarianService.saveLibrarian(librarianRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity findLibrarianById(@PathVariable int id){
        ResponseEntity response=librarianService.findLibrarianById(id);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/GetAllLibrarian")
    public ResponseEntity findAllLibrarian(){
        return librarianService.findAllLibrarian();
    }

    @PutMapping("/updateLibrarian/{id}")
    public ResponseEntity updateLibrarian(@PathVariable int id,@RequestBody LibrarianRequestDto librarianRequestDto){
        return librarianService.updateLibrarianById(id,librarianRequestDto);
    }

    @DeleteMapping("/deleteLibrarian/{id}")
    public ResponseEntity deleteLibrarian(@PathVariable int id){
        return librarianService.deleteLibrarianById(id);
    }
}
