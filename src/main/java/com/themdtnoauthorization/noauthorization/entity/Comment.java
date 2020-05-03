package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Date date;

    @ManyToOne
    private MedicalProfessional author;

    @ManyToOne
    private Disease disease;

    @OneToOne
    private Mdt mdt;

    public Comment(String comment) {
        this.comment = comment;

    }
}
