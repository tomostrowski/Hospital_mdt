package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.PatientRepo;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Patient update(Long id, Patient updatedPatient) {
            updatedPatient.setId(id);
            return patientRepo.save(updatedPatient);
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
        return diseaseRepo.findAllByPatientOrderByDiagnosisDate(patient);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Patient("Anna", "Kowal", "ATS210321", LocalDate.of(1982, 11, 14 ), "M", "+48 603 923 436", "t.z.ostrowski@gmail.com", "as123345"));
        save(new Patient( "Monika", "Waćkowska"));
        save(new Patient("Maria", "Żołądziejewska"));
        save(new Patient("Małgorzata", "Kościuszko"));
        save(new Patient("Barbara", "Słomińska"));
        save(new Patient("Anna", "Mickiewicz"));
    }
}
