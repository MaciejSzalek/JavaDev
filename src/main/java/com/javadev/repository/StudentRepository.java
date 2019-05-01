package com.javadev.repository;

import org.springframework.data.repository.CrudRepository;
import com.javadev.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}