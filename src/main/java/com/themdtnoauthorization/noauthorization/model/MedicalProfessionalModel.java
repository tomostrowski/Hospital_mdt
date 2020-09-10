package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicalProfessionalModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String professionalNumber;
    private String specialisation;

    public MedicalProfessionalModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
