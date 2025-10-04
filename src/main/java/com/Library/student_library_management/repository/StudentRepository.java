package com.Library.student_library_management.repository;

import com.Library.student_library_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    //With JPA Support, Using Fields and Attribute
    public Student findStudentByEmail(String email);

    public List<Student> findStudentByDept(String dept);

    public  List<Student> findStudentBySem(String sem);

    public List<Student> findStudentByDeptAndSem(String dept, String sem);

    public List<Student> findStudentByDeptOrSem(String dept, String sem);

    //Writing Own Sql Queries
    @Query(nativeQuery = true, value = "select * from student where email :email")
    public Student findByEmail(String email);

    @Query(nativeQuery = true,value = "select * from student where dept :dept")
    public List<Student> findByDept(String dept);


}
