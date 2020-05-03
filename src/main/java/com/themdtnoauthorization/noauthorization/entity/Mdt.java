package com.themdtnoauthorization.noauthorization.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Date DateOfReferralForMDT;

    @OneToOne
    private Disease disease;

    @OneToOne
    private Institution locationOfTreatment;

    private String summary;
    private Date reviewDate;
    private String additionalComments;
    private Date startDate;  //and time
    private Date endDate;

    @OneToMany
    private Set<MedicalProfessional> attendees;

    @OneToMany
    private Set<Comment> individualRecommendations;

    @OneToOne
    private Institution affiliation;

    public Mdt(String summary) {
        this.summary = summary;
    }
}
