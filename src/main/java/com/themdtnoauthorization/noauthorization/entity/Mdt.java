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
public class Mdt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate DateOfReferralForMDT;
    private String summary;
    private LocalDate reviewDate;
    private String additionalComments;
    private LocalDate startDate;  //and time
    private LocalDate endDate;
    private String locationOfTreatment;
    private String affiliation;
    private String isOpen; //zmienić na boolean

    @ManyToOne(cascade = CascadeType.ALL)
    private Disease disease;

    @OneToMany
    private Set<MedicalProfessional> attendees;

    @OneToMany
    private Set<Comment> comments;

    public Mdt(String summary) {
        this.summary = summary;
    }
}
