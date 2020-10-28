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
    @Column(length=1000)
    private String mammogram;
    @Column(length=1000)
    private String ultrasound;
    @Column(length=1000)
    private String ct;
    @Column(length=1000)
    private String mri;
    @Column(length=1000)
    private String other;

    @OneToOne(mappedBy = "imaging")
    private  Disease disease;
}
