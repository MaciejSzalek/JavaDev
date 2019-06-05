package com.javadev.repository;

import com.javadev.model.Attendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    List<Attendance> findByStudentId (Long id);

    @Query("SELECT a FROM Attendance a WHERE a.lectureId = :lecture AND a.studentId = :student ")
    List<Attendance> findAttendanceByLectureAndStudent(@Param("lecture") Long lectureId,
                                                       @Param("student") Long studentId);
}