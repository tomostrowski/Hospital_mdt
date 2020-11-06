package com.themdtnoauthorization.noauthorization.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Formula("concat(LAST_NAME,' ',FIRST_NAME)")
    private String name;
    private String username;
    private String role;
    private boolean isEnabled;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy="user")
    private MedicalProfessional medicalProfessional;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Comment> comments;

}

