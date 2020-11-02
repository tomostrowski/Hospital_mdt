package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedicalHistory {
    @Id
    private Long id;

    @Column(columnDefinition="TEXT")
    private String familyHistory;
    @Column(columnDefinition="TEXT")
    private String medicalHistory;
    @Column(columnDefinition="TEXT")
    private String medication;
    @Column(columnDefinition="TEXT")
    private String allergies;
    @Column(columnDefinition="TEXT")
    private String performanceStatus;

    @OneToOne
    private Patient patient;


}
