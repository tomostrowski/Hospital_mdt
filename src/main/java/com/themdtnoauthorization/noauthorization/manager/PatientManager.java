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

   @EventListener(ApplicationReadyEvent.class)
   public void fillDB(){
        save(new Patient("Weronika", "Rosatti", "AWQ210321", LocalDate.of(1980, 9, 14 ), "F", "+55 564 000 300", "rosati@gmail.com", "6as123345"));
        save(new Patient( "Magdalena", "Gorys", "TWM21000021", LocalDate.of(1981, 9, 15 ), "F", "+49 253 923 436", "gorys@gmail.com", "969856"));
        save(new Patient("Bożena", "Maliszewska", "OTWTH6427", LocalDate.of(1958, 10, 15 ), "F", "+22 125 923 111", "maria234@gmail.com", "162346"));
        save(new Patient("Agnieszka", "Bargos", "OWTSD210321", LocalDate.of(1963, 6, 14 ), "F", "+78 3214 923 111", "goska235@gmail.com", "864824511"));
        save(new Patient("Natalia", "Olensko", "PTSR210321", LocalDate.of(1972, 1, 14 ), "F", "+88 111 685 972", "barbara@gmail.com", "e503952"));
        save(new Patient("Tatiana", "Partyka", "KKS210321", LocalDate.of(1962, 9, 14 ), "F", "+411 777 963 436", "mickiewicz@gmail.com", "5234k6427"));
   }
}
