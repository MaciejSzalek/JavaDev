package com.javadev.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "attendance_table")
public class Attendance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendance_id")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "lecture_id")
    private Long lectureId;

    public Long getStudentId() {
        return studentId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }
}