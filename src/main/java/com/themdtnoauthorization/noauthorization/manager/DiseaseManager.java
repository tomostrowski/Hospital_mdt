package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.CancerInfoRepo;
import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.PatientRepo;
import com.themdtnoauthorization.noauthorization.dao.TreatmentHistoryRepo;
import com.themdtnoauthorization.noauthorization.entity.*;
import com.themdtnoauthorization.noauthorization.model.DiseaseModel;
import javassist.NotFoundException;
import javassist.runtime.Desc;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Filter;
import java.util.logging.StreamHandler;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DiseaseManager {
    private DiseaseRepo diseaseRepo;
    private PatientRepo patientRepo;
    private CancerInfoRepo cancerInfoRepo;
    private TreatmentHistoryRepo treatmentHistoryRepo;

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

    public Disease update(Long id, Disease update) {
        update.setId(id);
        return diseaseRepo.save(update);
    }

    public void deleteById(Long id){
        diseaseRepo.deleteById(id);
    }

//    public void addImaging(Long id, Long imagingId){
//        Disease disease = findById(id).orElseThrow(()-> new RuntimeException("Disease does not exist."));
//        Imaging imaging =
//    }


//    @EventListener(ApplicationReadyE{
//    event.class)
//    public void fillDb(){
//        save(new Disease("LEFT breast IDCG3 ER neg, PR neg, Her2 awaited (FISH) 28mm on US", LocalDate.of(2005, 11, 14 ),"Marek Mariusz Ostrowski","Grzegorz Waryński","Shah Hospital Kenia"));
//    }
}
