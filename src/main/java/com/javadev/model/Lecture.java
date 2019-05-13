package com.javadev.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lectures_table")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column (name = "lecture_id")
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "lecturer")
    private String lecturer;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private Set<Attendance> attendance = new HashSet<>();

    public Set<Attendance> getAttendance(){
        return attendance;
    }

    public void setAttendance(Set<Attendance> attendance) {
        this.attendance = attendance;
    }
    public void addAttendance(Attendance attendance){
        this.attendance.add(attendance);
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}