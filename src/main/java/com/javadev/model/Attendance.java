package com.javadev.model;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @Column(name = "attendance_status")
    private Boolean attendanceStatus;

    public Boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(Boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}