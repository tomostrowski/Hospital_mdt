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
    private Long id;
    @Column(length=1000)
    private String familyHistory;
    @Column(length=1000)
    private String medicalHistory;
    @Column(length=1000)
    private String medication;
    @Column(length=1000)
    private String allergies;
    @Column(length=1000)
    private String performanceStatus;

    @OneToOne
    private Patient patient;


}
