package com.student.student_library_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.student.student_library_management.enums.TransactionStatus;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate=null;

    @Column(name = "fine_amount",nullable = false)
    private int fineAmount=0;

    @Column(name = "pickup_expiry_date")
    private LocalDate pickupExpiryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status=TransactionStatus.PENDING;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    //Connection Relationship B/W Card and Transaction -> Using one card we can make many transaction for many books OneToMany
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "card_id")
    private  Card card;

    //Connection Relationship B/W Book and Transaction -> many transactions for one book ManyToOne
    @JsonBackReference
    @JoinColumn(name = "book_id")
    @ManyToOne
    private Book book;


}
/*
    id, borrowDate, dueDate, returnDate, fineAmount,
    status(PENDING / ISSUED / RETURNED),pickupExpiryDate
 */
