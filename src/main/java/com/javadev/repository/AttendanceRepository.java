package com.javadev.repository;

import com.javadev.model.Attendance;
import com.javadev.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    //@Query("select a from Attendance a where a.student_id= :student_id and a.lecture_id = :lecture_id ")
    //List<Attendance> findAttendanceByStudentAndLectureId(String student_id, String lecture_id);
    @Query("SELECT a FROM Attendance a WHERE a.lecture.id = :lecture AND a.student.id = :student ")
    List<Attendance> findAttendanceByLectureAndStudent(@Param("lecture") Long lectureId,
                                                         @Param("student") Long studentId);
}