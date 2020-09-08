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
        return diseaseRepo.findAllByPatientOrderByDiagnosisDateDesc(patient);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB(){
//        save(new Patient("Anna", "Kowal", "AWS210321", LocalDate.of(1982, 11, 14 ), "F", "+55 524 000 300", "annakowal@gmail.com", "as123345"));
//        save(new Patient( "Monika", "Waćkowska", "SSS21000021", LocalDate.of(1989, 9, 10 ), "F", "+49 253 923 436", "moniaa@gmail.com", "9856"));
//        save(new Patient("Maria", "Żołądziejewska", "WTH6427", LocalDate.of(1928, 10, 1 ), "F", "+22 125 923 111", "maria234@gmail.com", "12346"));
//        save(new Patient("Małgorzata", "Kościuszko", "ATS210321", LocalDate.of(1977, 6, 14 ), "F", "+78 953 923 111", "goska235@gmail.com", "824511"));
//        save(new Patient("Barbara", "Słomińska", "ATS210321", LocalDate.of(1972, 1, 14 ), "F", "+48 124 685 972", "barbara@gmail.com", "e03952"));
//        save(new Patient("Anna", "Mickiewicz", "ATS210321", LocalDate.of(1962, 12, 14 ), "F", "+48 777 963 436", "mickiewicz@gmail.com", "234k6427"));
//    }
}
