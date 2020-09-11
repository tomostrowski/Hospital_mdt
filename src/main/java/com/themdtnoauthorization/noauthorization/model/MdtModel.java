package com.themdtnoauthorization.noauthorization.model;


import com.themdtnoauthorization.noauthorization.entity.Comment;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Institution;
import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MdtModel {
    private Long id;
    private LocalDateTime DateOfReferralForMDT;
    private InstitutionModel locationOfTreatment;
    private String summary;
    private Date reviewDate;
    private String additionalComments;
    private LocalDateTime startDate;  //and time
    private LocalDateTime endDate;
    private Set<MedicalProfessional> attendees;
    private Set<Comment> comments;
    private InstitutionModel affiliation;
}
