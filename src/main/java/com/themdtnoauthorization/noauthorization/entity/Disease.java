package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate diagnosisDate;

    @OneToOne
    private MedicalProfessional diagnosingPhysician; //

    @OneToOne
    private MedicalProfessional referringPhysician;

    @OneToOne
    private Institution placeOfDiagnosis;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private CancerInfo cancerInfo;

    @OneToOne
    private TreatmentHistory treatmentHistory;

    @OneToOne
    private MedicalHistory medicalHistory;

    @OneToMany
    private Set<Mdt> mdts;

    public Disease(String name) {
        this.name = name;
    }
}
