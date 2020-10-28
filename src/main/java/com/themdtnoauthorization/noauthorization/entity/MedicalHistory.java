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
    private String familyHistory;
    private String medicalHistory;
    private String medication;
    private String allergies;
    private String performanceStatus;

    @OneToOne
    private Patient patient;


}
