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
public class TreatmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biopsy;
    private String surgery;
    private String radiotherapy;
    private String endocrineTreatment;
    private String chemotherapy;
    private String immunotherapy;
    private String physiotherapy;
    private String other;

    @OneToOne
    private Disease disease;

    public TreatmentHistory(String biopsy, String surgery, String radiotherapy, String endocrineTreatment, String chemotherapy, String immunotherapy, String physiotherapy, String other) {
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
