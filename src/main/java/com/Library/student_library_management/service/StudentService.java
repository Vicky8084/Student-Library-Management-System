package com.Library.student_library_management.service;

import com.Library.student_library_management.Converter.StudentConverter;
import com.Library.student_library_management.enums.CardStatus;
import com.Library.student_library_management.model.Card;
import com.Library.student_library_management.model.Student;
import com.Library.student_library_management.repository.StudentRepository;
import com.Library.student_library_management.requestDTO.StudentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    private StudentService (StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public String addStudent(StudentRequestDTO studentRequestDTO){
        Student student=StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDTO);
        Card card=new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setStudent(student);
        card.setBloodGroup(studentRequestDTO.getBloodGroup());
        student.setCard(card);

        studentRepository.save(student);
        return("Student and Card Saved Successfully");
    }

    public Student findStudentById(int id){
        Optional<Student> optionalStudent=studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        else {
            return null;
        }
    }

    public List<Student> findAllStudent(){
        List<Student> studentList=studentRepository.findAll();
        return studentList;
    }


    public List<Student> fineALlByPages(int pageNumber, int pageSize,String sortBy, String order){
        if(order.equalsIgnoreCase("Ascending")){

            return studentRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).ascending())).getContent();
        }
        else{
            return studentRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending())).getContent();
        }
    }

//    public List<Student> findStudentByPages(int pageNumber, int pageSize){
//        List<Student> studentList=studentRepository.findAll(PageRequest.of(pageNumber,pageSize)).getContent();
//        return studentList;
//    }

    public String updateStudent(int id, StudentRequestDTO studentRequestDTO){
        Student student=findStudentById(id);
        if(student!=null){
            student.setName(studentRequestDTO.getName());
            student.setMobileNumber(studentRequestDTO.getMobileNumber());
            student.setEmail(studentRequestDTO.getEmail());
            student.setDept(studentRequestDTO.getDept());
            student.setSem(studentRequestDTO.getSem());
            student.setAddress(studentRequestDTO.getAddress());
            student.setDob(studentRequestDTO.getDob());
            student.setGender(studentRequestDTO.getGender());
            student.setSection(studentRequestDTO.getSection());
            studentRepository.save(student);
            return "Student updated Successfully";
        }
        else{
            return "Student not Fund ...!!!";
        }
    }

    public String deleteStudent(int id){
        studentRepository.deleteById(id);
        return "Student Deleted Successfully...!!!";
    }

    public Student findStudentByEmail(String email){
        return studentRepository.findStudentByEmail(email);
    }

    public List<Student> findStudentByDept(String dept){
        return studentRepository.findStudentByDept(dept);
    }

    public  List<Student> findStudentBySem(String sem){
        return studentRepository.findStudentBySem(sem);
    }

    public Student findByEmail(String email){
        return studentRepository.findByEmail(email);
    }
}
