package com.themdtnoauthorization.noauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ImagingModel {
    private Long id;
    private String mammogram;
    private String ultrasound;
    private String ct;
    private String mri;
    private String other;
}
