package com.themdtnoauthorization.noauthorization.model;

import com.themdtnoauthorization.noauthorization.entity.CancerInfo;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import com.themdtnoauthorization.noauthorization.entity.TreatmentHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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
//    private Patient patient;
    private CancerInfoModel cancerInfo;
    private TreatmentHistoryModel treatmentHistory;
    private Set<MdtModel> mdts;
    private ImagingModel imaging;

}
