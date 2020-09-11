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

    @ManyToOne(cascade = CascadeType.ALL)
    private Disease disease;

    @OneToOne
    private Institution locationOfTreatment;

    private String summary;
    private Date reviewDate;
    private String additionalComments;
    private LocalDateTime startDate;  //and time
    private LocalDateTime endDate;

    @OneToMany
    private Set<MedicalProfessional> attendees;

    @OneToMany
    private Set<Comment> comments;

    @OneToOne
    private Institution affiliation;

    public Mdt(String summary) {
        this.summary = summary;
    }
}
