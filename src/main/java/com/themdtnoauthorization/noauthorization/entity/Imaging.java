package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Imaging {
    @Id
    private Long id;

    @Column(columnDefinition="TEXT")
    private String mammogram;

    @Column(columnDefinition="TEXT")
    private String ultrasound;
    @Column(columnDefinition="TEXT")
    private String ct;
    @Column(columnDefinition="TEXT")
    private String mri;
    @Column(columnDefinition="TEXT")
    private String other;

    @OneToOne(mappedBy = "imaging")
    private  Disease disease;
}
