package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.PatientRepo;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PatientManager {

    //można usunąć?
    private PatientRepo patientRepo;
    private DiseaseRepo diseaseRepo;
//    private DiseaseRepo diseaseRepo;

    public PatientManager(PatientRepo patientRepo, DiseaseRepo diseaseRepo) {
        this.patientRepo = patientRepo;
        this.diseaseRepo = diseaseRepo;
//        this.diseaseRepo = diseaseRepo;
    }

    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }

    public Optional<Patient> findById(Long id){
        return patientRepo.findById(id);
    }

    public List<Patient> findAll(){
        return patientRepo.findAll();
    }

    public void deleteById(Long id){
        patientRepo.deleteById(id);
    }

    public Set<Disease> findAllDiseasesByPatientId(Long id){
        Patient patient = patientRepo.findById(id).get();
        return diseaseRepo.findAllByPatient(patient);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Patient("Tomasz", "Ostrowski", "ATS210321", new Date(), "M", "+48 603 923 436", "t.z.ostrowski@gmail.com", 123345));
        save(new Patient( "Mateusz", "Waćkowski"));
        save(new Patient("Piotr", "Żołądziejewski"));
        save(new Patient("Baltazar", "Kościuszko"));
        save(new Patient("Arkadiusz", "Słomiński"));
        save(new Patient("Grzegorz", "Mickiewicz"));
    }
}
