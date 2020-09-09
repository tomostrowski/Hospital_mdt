package com.themdtnoauthorization.noauthorization.model;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientModel {
    private Long id;
    private String givenName;
    private String surname;
    private String idNumber;
    private String mobileNumber;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private String patientNumber;
    private List<String> diseaseNames;

    public PatientModel(String givenName, String surname) {
        this.givenName = givenName;
        this.surname = surname;
    }

    public PatientModel(String givenName, String surname, LocalDate dateOfBirth, String gender, String patientNumber) {
        this.givenName = givenName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.patientNumber = patientNumber;
    }

}
