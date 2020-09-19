package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentModel {
    private Long id;
    private String text;
    private LocalDate date;   //z mienić na LocalDateTime  z czasem
    private String author;
//    private DiseaseModel disease;
//    private MdtModel mdt;
    private String wasEdited; //zmienic na Boolean
}
