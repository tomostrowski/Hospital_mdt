package com.themdtnoauthorization.noauthorization.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table()
public class Mdt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfReferralForMDT;
    @Column(length = 1000)
    private String summary;
    private LocalDate reviewDate;
    @Column(length = 1000)
    private String additionalComments;
    private LocalDate startDate;  //and time
    private LocalDate endDate;
    private String locationOfTreatment;
    private String affiliation;
//    private String isOpen; //zmienić na boolean

    @ManyToOne(cascade = CascadeType.ALL)
    private Disease disease;

    @OneToMany
    private Set<MedicalProfessional> attendees;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="mdt")
    private List<Comment> comments;

    public Mdt(String summary) {
        this.summary = summary;
    }
}
