package com.javadev.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.javadev.model.Student;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.mail = :mail OR s.indexNumber = :indexNumber")
    Optional<Student> findStudentByMailOrIndexNumber(@Param("mail") String mail,
                                                     @Param("indexNumber") String indexNumber);

}