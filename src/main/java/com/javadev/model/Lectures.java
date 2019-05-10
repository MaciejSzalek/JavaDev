package com.javadev.model;

import javax.persistence.*;

@Entity
@Table(name = "lectures_table")
public class Lectures {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column (name = "id")
    private Long id;
}