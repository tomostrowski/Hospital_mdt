package com.themdtnoauthorization.noauthorization.model;

import com.themdtnoauthorization.noauthorization.entity.CancerInfo;
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

public class DiseaseModelList {

    private Long id;
    private String name;
    private LocalDate diagnosisDate;


    public DiseaseModelList(String name, LocalDate diagnosisDate, String diagnosingPhysician, String referringPhysician, String placeOfDiagnosis) {
    this.name = name;
    this.diagnosisDate = diagnosisDate;
    }
}
