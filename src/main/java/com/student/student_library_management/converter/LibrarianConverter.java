package com.student.student_library_management.converter;


import com.student.student_library_management.model.Librarian;
import com.student.student_library_management.requestDTO.LibrarianRequestDto;

public class LibrarianConverter {
    public static Librarian convertLibrarianRequestDtoIntoLibrarian(LibrarianRequestDto librarianRequestDto){
        Librarian librarian=new Librarian();

        librarian.setName(librarianRequestDto.getName());
        librarian.setEmail(librarianRequestDto.getEmail());
        librarian.setPassword(librarianRequestDto.getPassword());
        librarian.setMobileNumber(librarianRequestDto.getMobileNumber());
        return librarian;
    }
}