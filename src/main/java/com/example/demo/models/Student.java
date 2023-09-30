package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "id")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastname;
    private String name;
    private String streamGroup;
    private Date dateOfAdmission;

    private boolean isExpelled;

    public Student() {}

    public Student(String lastname, String name, String streamGroup, Date dateOfAdmission) {
        this.lastname = lastname;
        this.name = name;
        this.streamGroup = streamGroup;
        this.dateOfAdmission = dateOfAdmission;
        this.isExpelled = false;
    }
}
