package com.themdtnoauthorization.noauthorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class CancerInfo {
    @Id
    private Long id;
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
    private String typeOther;
    private int size;
    private String margins;
    private double MarginNumber;
    private String positiveLymphNodes;

    public CancerInfo(String summary) {
        this.summary = summary;
    }

    public CancerInfo(Long id) {
        this.id = id;
    }
}
