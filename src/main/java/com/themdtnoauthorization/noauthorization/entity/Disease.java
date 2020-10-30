package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=1000)
    private String name;
    private LocalDate diagnosisDate;
    private String placeOfDiagnosis;

    @OneToOne
    private MedicalProfessional diagnosingPhysician;

    @OneToOne
    private MedicalProfessional referringPhysician;

    public Disease(String name, LocalDate diagnosisDate, MedicalProfessional diagnosingPhysician, MedicalProfessional referringPhysician, String placeOfDiagnosis) {
    this.name = name;
    this.diagnosisDate = diagnosisDate;
    this.diagnosingPhysician = diagnosingPhysician;
    this.referringPhysician = referringPhysician;
    this.placeOfDiagnosis = placeOfDiagnosis;
    }

//    @OneToOne
//    private Institution placeOfDiagnosis;

    @ManyToOne
//            (cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Patient patient;
//
    @OneToOne
    private CancerInfo cancerInfo;
//
    @OneToOne
    private TreatmentHistory treatmentHistory;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="disease")
    private List<Mdt> mdts;

    @OneToOne
    private Imaging imaging;
//    @OneToMany
//    private Set<Comment> comments;


}
