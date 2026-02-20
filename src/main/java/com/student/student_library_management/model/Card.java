package com.student.student_library_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.student.student_library_management.enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number",nullable = false,unique = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private CardStatus status=CardStatus.ACTIVE;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    //Connection Relationship Between Student and Card
    //Jahan @JoinColumn likha ho - wahan foreign key banegi
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "student_id")  //Foreign key YAHAN banegi
    private Student student;

    //Connection Relationship B/W Card and Book -> Using one card we can borrow many books OneToMany
    @JsonManagedReference
    @OneToMany(mappedBy = "card")
    private List<Book> books;

    //Connection Relationship B/W Card and Transaction -> Using one card we can make many transaction for many books OneToMany
    @JsonManagedReference
    @OneToMany(mappedBy = "card")
    private List<Transaction> transactions;

}
/*
    Card :-cardId ,createdAt, updatedAt, cardNumber,
    status(ACTIVE,INACTIVE,BLOCKED,DELETED).
 */
