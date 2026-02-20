package com.student.student_library_management.service;

import com.student.student_library_management.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
@Service
public class CardService {
    public static final long BASE_SEQUENCE=2342;
    CardRepository cardRepository;
    @Autowired
    public CardService(CardRepository cardRepository){
        this.cardRepository=cardRepository;
    }
    public String generateCardNumber(){
        String prefix="VIC";
        String year=String.valueOf(Year.now().getValue());
        long currentCount=cardRepository.count();
        long nextSequence=BASE_SEQUENCE+currentCount;
        String sequence=String.format("%04d",nextSequence);
        return prefix+year+sequence;
    }
}
