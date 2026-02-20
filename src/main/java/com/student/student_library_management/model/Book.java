package com.student.student_library_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.student.student_library_management.enums.BookStatus;
import com.student.student_library_management.enums.Category;
import com.student.student_library_management.enums.Edition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title",nullable = false,unique = true)
    private String title;

    @Column(name = "publisher_name",nullable = false)
    private String publisherName;

    @Column(name = "publisher_date",nullable = false)
    private LocalDate publisherDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "category",nullable = false)
    private Category category;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "page_number",nullable = false)
    private int pageNumber;

    @Column(name = "total_copies",nullable = false)
    private int totalCopies;

    @Column(name = "available_copies",nullable = false)
    private int availableCopies;

    @Column(name = "rack_number",nullable = false)
    private String rackNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private BookStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "edition",nullable = false)
    private Edition edition;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    //Connection Relationship B/W Card and Book -> Using one card we can borrow many books OneToMany
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    //Connection Relationship B/W Book and Transaction -> many transactions for one book ManyToOne
    @JsonManagedReference
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactions;

    //Connection Relationship B/W Book and Author -> one author can write many books OneToMany
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;



}
/*
    id, title, publisherName, PublisherDate, category, price, pageNumber,
    totalCopies, availableCopies, rackNumber, status, edition.
 */


