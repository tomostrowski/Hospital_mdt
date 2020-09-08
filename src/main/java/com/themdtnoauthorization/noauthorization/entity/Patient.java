package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

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
    @Formula("trim(GIVEN_NAME)")
    private String givenName;
    private String surname;
    private String idNumber;

    private LocalDate dateOfBirth;


    @Formula("floor(((day(current_date())+month(current_date())*30+year(current_date())*365.25)" +
            "-(day(DATE_OF_BIRTH)+month(DATE_OF_BIRTH)*30+year(DATE_OF_BIRTH)*365.25))/365.25)")
    private int age;

    private String gender;
    private String mobileNumber;
    private String email;
    private String patientNumber;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @OrderBy("diagnosisDate DESC")
    private Set<Disease> diseases;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private MedicalHistory medicalHistory;

    public Patient(String givenName, String surname) {
        this.givenName = givenName;
        this.surname = surname;
    }

    public Patient(String givenName, String surname, String idNumber, LocalDate dateOfBirth, String gender, String mobileNumber, String email, String patientNumber) {
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
