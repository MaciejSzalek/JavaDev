package com.javadev.repository;

import com.javadev.model.Role;
import com.javadev.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.student = :student")
    Role findRoleByStudent(@Param("student")Student student);
}