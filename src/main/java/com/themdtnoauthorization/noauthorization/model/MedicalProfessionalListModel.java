package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicalProfessionalListModel {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String specialisation;
}
