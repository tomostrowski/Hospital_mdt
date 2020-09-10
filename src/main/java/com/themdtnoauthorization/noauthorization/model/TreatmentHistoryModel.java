package com.themdtnoauthorization.noauthorization.model;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TreatmentHistoryModel {

    private Long id;
    private String biopsy;
    private String surgery;
    private String radiotherapy;
    private String endocrineTreatment;
    private String chemotherapy;
    private String immunotherapy;
    private String physiotherapy;
    private String other;

    public TreatmentHistoryModel(Long id) {
        this.id = id;
    }
    public TreatmentHistoryModel(String biopsy, String surgery, String radiotherapy, String endocrineTreatment, String chemotherapy, String immunotherapy, String physiotherapy, String other) {
        this.biopsy = biopsy;
        this.surgery = surgery;
        this.radiotherapy = radiotherapy;
        this.endocrineTreatment = endocrineTreatment;
        this.chemotherapy = chemotherapy;
        this.immunotherapy = immunotherapy;
        this.physiotherapy = physiotherapy;
        this.other = other;
    }
}
