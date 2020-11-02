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
    @Column(columnDefinition="TEXT")
    private String biopsy;
    @Column(columnDefinition="TEXT")
    private String surgery;
    @Column(columnDefinition="TEXT")
    private String radiotherapy;
    @Column(columnDefinition="TEXT")
    private String endocrineTreatment;
    @Column(columnDefinition="TEXT")
    private String chemotherapy;
    @Column(columnDefinition="TEXT")
    private String immunotherapy;
    @Column(columnDefinition="TEXT")
    private String physiotherapy;
    @Column(columnDefinition="TEXT")
    private String other;

    @OneToOne
    private Disease disease;

}
