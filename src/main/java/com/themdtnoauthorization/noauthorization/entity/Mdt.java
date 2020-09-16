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
    private LocalDateTime DateOfReferralForMDT;
    private String summary;
    private LocalDateTime reviewDate;
    private String additionalComments;
    private LocalDateTime startDate;  //and time
    private LocalDateTime endDate;
    private String locationOfTreatment;
    private String affiliation;
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
