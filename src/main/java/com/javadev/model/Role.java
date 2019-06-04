package com.javadev.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Student student;

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student){
        this.student = student;
    }


    public Long getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

}