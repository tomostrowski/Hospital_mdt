package com.themdtnoauthorization.noauthorization.model;

import com.themdtnoauthorization.noauthorization.entity.CancerInfo;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import com.themdtnoauthorization.noauthorization.entity.TreatmentHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DiseaseModel {

    private Long id;
    private String name;
    private LocalDate diagnosisDate;
    private String diagnosingPhysician;
    private String referringPhysician;
    private String placeOfDiagnosis;
    private Patient patient;
    private CancerInfo cancerInfo;
    private TreatmentHistory treatmentHistory;

    public DiseaseModel(String name, LocalDate diagnosisDate, String diagnosingPhysician, String referringPhysician, String placeOfDiagnosis) {
    this.name = name;
    this.diagnosisDate = diagnosisDate;
    this.referringPhysician = referringPhysician;
    this.placeOfDiagnosis = placeOfDiagnosis;
    }
}
