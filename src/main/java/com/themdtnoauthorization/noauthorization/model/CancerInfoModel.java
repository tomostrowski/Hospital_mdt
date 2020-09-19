package com.themdtnoauthorization.noauthorization.model;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CancerInfoModel {

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
    private int upTen;
    private String typeOther;
    private int size;
    private String positiveLymphNodes;
    private String dcis;
    private double superior;
    private double inferior;
    private double lateral;
    private double medial;

    private Disease disease;

    public CancerInfoModel(String summary) {
        this.summary = summary;
    }

    public CancerInfoModel(Long id) {
        this.id = id;
    }
}
