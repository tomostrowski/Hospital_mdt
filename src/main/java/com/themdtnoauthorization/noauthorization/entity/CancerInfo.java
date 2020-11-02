package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class CancerInfo {
    @Id
    private Long id;
    @Column(columnDefinition="TEXT")
    private String summary;
    private String Tc;   //c for clinical
    private String Nc;
    private String Mc;
    private String Tp;  //p for pathological
    private String Np;
    private String Mp;
    private String type;
    private String grade;
    private String biomarkers;
    private String ki67;
    private int upTen;
    private String typeOther;
    private int size;
    private double superior;
    private double inferior;
    private double lateralMargin; // poprzednia nazwa lateral jest słowem kluczowym w MySQL
    private double medial;
    private String positiveLymphNodes;
    private String dcis;

    @OneToOne(cascade = CascadeType.ALL)
    private Disease disease;
}
