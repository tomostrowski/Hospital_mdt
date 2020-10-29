package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MdtListModel {
        private Long id;
        private LocalDate DateOfReferralForMDT;
        private String summary;
        //    private InstitutionModel locationOfTreatment;
        private String locationOfTreatment;
        private LocalDate reviewDate;
        private String additionalComments;
        private LocalDate startDate;  //and time
        private LocalDate endDate;
        private Set<CommentModel> comments;
//        private Set<MedicalProfessionalModel> attendees;
//        private Set<CommentModel> comments;
        private String affiliation;
//    private InstitutionModel affiliation;

}
