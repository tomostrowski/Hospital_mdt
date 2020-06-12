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
}
