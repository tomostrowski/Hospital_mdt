package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String givenName;
    private String surname;
    private String idNumber;
    private LocalDate dateOfBirth;
    private int age;
    private String gender;
    private String mobileNumber;
    private String email;
    private int patientNumber;

    @OneToMany
    private Set<Disease> diseases;

    public Patient(String givenName, String surname) {
        this.givenName = givenName;
        this.surname = surname;
    }

    public Patient(String givenName, String surname, String idNumber, LocalDate dateOfBirth, String gender, String mobileNumber, String email, int patientNumber) {
        this.givenName = givenName;
        this.surname = surname;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.patientNumber = patientNumber;
    }
}
