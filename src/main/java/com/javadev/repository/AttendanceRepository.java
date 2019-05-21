package com.javadev.repository;

import com.javadev.model.Attendance;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
}