package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicalHistoryModel {

    private Long id;
    private String familyHistory;
    private String medication;
    private String allergies;
    private String performanceStatus;

    public MedicalHistoryModel(String familyHistory, String medication, String allergies, String performanceStatus) {
        this.familyHistory = familyHistory;
        this.medication = medication;
        this.allergies = allergies;
        this.performanceStatus = performanceStatus;
    }
}
