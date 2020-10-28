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
    private Long id;
    @Column(length=1000)
    private String biopsy;
    @Column(length=1000)
    private String surgery;
    @Column(length=1000)
    private String radiotherapy;
    @Column(length=1000)
    private String endocrineTreatment;
    @Column(length=1000)
    private String chemotherapy;
    @Column(length=1000)
    private String immunotherapy;
    @Column(length=1000)
    private String physiotherapy;
    @Column(length=1000)
    private String other;

    @OneToOne
    private Disease disease;

}
