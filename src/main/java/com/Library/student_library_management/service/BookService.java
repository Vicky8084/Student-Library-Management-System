package com.Library.student_library_management.service;

import com.Library.student_library_management.Converter.BookConverter;
import com.Library.student_library_management.model.Author;
import com.Library.student_library_management.model.Book;
import com.Library.student_library_management.model.Card;
import com.Library.student_library_management.repository.AuthorRepository;
import com.Library.student_library_management.repository.BookRepository;
import com.Library.student_library_management.repository.CardRepository;
import com.Library.student_library_management.requestDTO.BookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CardRepository cardRepository;
    @Autowired
    private BookService(BookRepository bookRepository,
                        AuthorRepository authorRepository,
                        CardRepository cardRepository){
        this.bookRepository=bookRepository;
        this.cardRepository=cardRepository;
        this.authorRepository=authorRepository;
    }
    public String addBook(BookRequestDTO bookRequestDTO){
        Book book=BookConverter.convertBookRequestDtoIntoBook((bookRequestDTO));
        Author author=authorRepository.findById(bookRequestDTO.getAuthorIdd()).get();
        book.setAuthor(author);
        Card card=cardRepository.findById(bookRequestDTO.getCardId()).get();
        book.setCard(card);
        bookRepository.save(book);
        return"Book Saved Successfully";
    }
    public Book findBookById(@PathVariable int id){
        Optional<Book> bookOptional= bookRepository.findById(id);
        if(bookOptional.isPresent()){
            return bookOptional.get();
        }
        else {
            return null;
        }
    }

    public List<Book> findAllBook(){
        List<Book> listBook=bookRepository.findAll();
        return listBook;
    }

    public String updateBook(int id, BookRequestDTO bookRequestDTO){
        Book book=findBookById(id);
        book.setTitle(bookRequestDTO.getTitle());
        book.setPublisherName(bookRequestDTO.getPublisherName());
        book.setPublishDate(bookRequestDTO.getPublishDate());
        book.setCategory(bookRequestDTO.getCategory());
        book.setPrice(bookRequestDTO.getPrice());
        book.setPageNumber(bookRequestDTO.getPageNumber());
        book.setRackNumber(bookRequestDTO.getRackNumber());
        book.setAvailability(bookRequestDTO.isAvailability());
        book.setEdition(bookRequestDTO.getEdition());
        bookRepository.save(book);
        return "Book updated Successfully...!!";
    }

    public String deleteBookById(int id){
        bookRepository.deleteById(id);
        return "Book deleted Successfully..!!!";
    }
}

