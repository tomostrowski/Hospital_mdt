package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String diagnosingPhysician;
    private String referringPhysician;
    private String placeOfDiagnosis;
    public Disease(String name, LocalDate diagnosisDate, String diagnosingPhysician, String referringPhysician, String placeOfDiagnosis) {
    this.name = name;
    this.diagnosisDate = diagnosisDate;
    this.referringPhysician = referringPhysician;
    this.placeOfDiagnosis = placeOfDiagnosis;
    }

//    @OneToOne
//    private MedicalProfessional diagnosingPhysician; //
//
//    @OneToOne
//    private MedicalProfessional referringPhysician;
//
//    @OneToOne
//    private Institution placeOfDiagnosis;
//
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Patient patient;
//
    @OneToOne
    private CancerInfo cancerInfo;
//
    @OneToOne
    private TreatmentHistory treatmentHistory;



//    @OneToMany
//    private Set<Mdt> mdts;



}
