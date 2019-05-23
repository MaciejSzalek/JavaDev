package com.javadev.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "student_table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @Column (name = "student_id", updatable = false, nullable = false)
    private Long id;

    @Column (name = "firstName")
    private String firstName;

    @Column (name = "lastName")
    private String lastName;

    @Column (name = "mail")
    private String mail;

    @Column (name = "studyYear")
    private String studyYear;

    @Column (name = "studyField")
    private String studyField;

    @Column (name = "indexNumber")
    private String indexNumber;

    @Column (name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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