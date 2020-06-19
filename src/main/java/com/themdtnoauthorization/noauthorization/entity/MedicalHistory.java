package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String familyHistory;
    private String medication;
    private String allergies;
    private String performanceStatus;

    @OneToOne
    private Patient patient;

    public MedicalHistory(String familyHistory, String medication, String allergies, String performanceStatus) {
        this.familyHistory = familyHistory;
        this.medication = medication;
        this.allergies = allergies;
        this.performanceStatus = performanceStatus;
    }
}
