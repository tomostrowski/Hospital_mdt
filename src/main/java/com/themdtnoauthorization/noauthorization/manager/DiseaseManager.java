package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.PatientRepo;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiseaseManager {
    private DiseaseRepo diseaseRepo;
    private PatientRepo patientRepo;

    public DiseaseManager(DiseaseRepo diseaseRepo, PatientRepo patientRepo) {
        this.diseaseRepo = diseaseRepo;
        this.patientRepo = patientRepo;
    }

    public Optional<Disease> findById(Long id){
        return diseaseRepo.findById(id);
    }
    public Iterable<Disease> findAll(){
        return diseaseRepo.findAll();
    }

    public Disease save(Disease disease){
        return diseaseRepo.save(disease);
    }

    public void deleteById(Long id){
        diseaseRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        save(new Disease("Breast Cancer XCSS"));
        save(new Disease("Breast Cancer VTT2"));
    }
}
