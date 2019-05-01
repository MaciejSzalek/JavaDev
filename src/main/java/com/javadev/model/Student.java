package com.javadev.model;

import javax.persistence.*;

@Entity
@Table (name = "student_table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @Column (name = "id", updatable = false, nullable = false)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "mail")
    private String mail;

    @Column (name = "study_year")
    private String studyYear;

    @Column (name = "study_field")
    private String studyField;

    @Column (name = "index_number")
    private String indexNumber;

    @Column (name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public String getStudyField() {
        return studyField;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}