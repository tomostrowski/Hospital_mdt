package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentModel {
    private Long id;
    private String text;
    private LocalDateTime date;
    private String author;
    private DiseaseModel disease;
    private MdtModel mdt;

}
