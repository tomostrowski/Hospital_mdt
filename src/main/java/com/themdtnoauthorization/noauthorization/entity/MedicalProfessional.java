package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class MedicalProfessional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Formula("lastName"+" "+"fistName")
    private String name;
    private String email;
    private String mobileNumber;
    private String professionalNumber;
    private String specialisation;

    @OneToMany
    private Set<Comment> commments;

    public MedicalProfessional(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
