package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientListModel {
    private Long id;
    private String givenName;
    private String surname;
    private LocalDate dateOfBirth;
    private String gender;
    private String patientNumber;
    private Set<DiseaseModel> diseases;

    public PatientListModel(String givenName, String surname) {
        this.givenName = givenName;
        this.surname = surname;
    }

    public PatientListModel(String givenName, String surname, LocalDate dateOfBirth, String gender, String patientNumber) {
        this.givenName = givenName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.patientNumber = patientNumber;
    }


}
