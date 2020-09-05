package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.PatientRepo;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Optional<Disease> findByToken(String token){
        return diseaseRepo.findByToken(token);
    }

    public Disease save(Disease disease){
        return diseaseRepo.save(disease);
    }

    public Disease update(Long id, Disease update) {
        update.setId(id);
        return diseaseRepo.save(update);
    }

    public void deleteById(Long id){
        diseaseRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        save(new Disease("LEFT breast IDCG3 ER neg, PR neg, Her2 awaited (FISH) 28mm on US", LocalDate.of(2005, 11, 14 ),"Marek Mariusz Ostrowski","Grzegorz Waryński","Shah Hospital Kenia"));
    }
}
