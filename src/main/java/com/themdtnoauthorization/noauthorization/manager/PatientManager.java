package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.PatientRepo;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.MedicalHistory;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import com.themdtnoauthorization.noauthorization.entity.TreatmentHistory;
import com.themdtnoauthorization.noauthorization.model.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


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
//do listy pacjentó jest mniej danych!
    public Set<PatientListModel> getPatientList() {
        Set<Patient> patientSet =  new LinkedHashSet<>(patientRepo.findAll());
        if (patientSet.size() > 0) {
            Set<PatientListModel> patientListModels = new LinkedHashSet<>();
            for (Patient patient : patientSet) {
                PatientListModel model = new PatientListModel();
                model.setId(patient.getId());
                model.setGivenName(patient.getGivenName());
                model.setSurname(patient.getSurname());
                model.setGender(patient.getGender());
                model.setDateOfBirth(patient.getDateOfBirth());
                model.setPatientNumber(patient.getPatientNumber());
                model.setDiseases(getDiseaseListSet(patient));
                patientListModels.add(model);
            }
            return patientListModels;
        } else return new LinkedHashSet<PatientListModel>();
    }

public Set<DiseaseModelList> getDiseaseListSet(Patient patient){
Set<Disease> diseaseSet = new LinkedHashSet<>(patient.getDiseases());
        if (diseaseSet.size() > 0){
            Set<DiseaseModelList> diseaseModelSet = new LinkedHashSet<>();
            for (Disease disease : diseaseSet) {
                DiseaseModelList model = new DiseaseModelList();
                model.setId(disease.getId());
                model.setName(disease.getName());
                model.setDiagnosisDate(disease.getDiagnosisDate());

                diseaseModelSet.add(model);
            }
            return diseaseModelSet;
        } else return new LinkedHashSet<DiseaseModelList>();
}

    public Optional<PatientModel> getPatient(Patient patient) {
                PatientModel model = new PatientModel();
                model.setId(patient.getId());
                model.setGivenName(patient.getGivenName());
                model.setSurname(patient.getSurname());
                model.setGender(patient.getGender());
                model.setDateOfBirth(patient.getDateOfBirth());
                model.setPatientNumber(patient.getPatientNumber());
                model.setEmail(patient.getEmail());
                model.setMobileNumber(patient.getMobileNumber());
                model.setIdNumber(patient.getIdNumber());
                model.setDiseases(getDiseaseSet(patient));
                model.setMedicalHistory(getMedicalHistoryModel(patient));
            return Optional.of(model);
    }

    public Set<DiseaseModel> getDiseaseSet(Patient patient){
        Set<Disease> diseaseSet = new LinkedHashSet<>(patient.getDiseases());
        if (diseaseSet.size() > 0){
            Set<DiseaseModel> diseaseModelSet = new LinkedHashSet<>();
            for (Disease disease : diseaseSet) {
                DiseaseModel model = new DiseaseModel();
                model.setId(disease.getId());
                model.setName(disease.getName());
                model.setDiagnosisDate(disease.getDiagnosisDate());
                model.setDiagnosingPhysician(disease.getDiagnosingPhysician());
                model.setReferringPhysician(disease.getReferringPhysician());
                model.setPlaceOfDiagnosis(disease.getPlaceOfDiagnosis());
//                model.setCancerInfo(get);
//                model.setTreatmentHistory();
                diseaseModelSet.add(model);
            }
            return diseaseModelSet;
        } else return new LinkedHashSet<DiseaseModel>();
    }

    public MedicalHistoryModel getMedicalHistoryModel(Patient patient){
        MedicalHistory medicalHistory = patient.getMedicalHistory();
        if (medicalHistory != null) {
        MedicalHistoryModel model = new MedicalHistoryModel();
        model.setId(medicalHistory.getId());
        model.setFamilyHistory(medicalHistory.getFamilyHistory());
        model.setMedication(medicalHistory.getMedication());
        model.setPatient(medicalHistory.getAllergies());
        model.setPerformanceStatus(medicalHistory.getPerformanceStatus());
        return model;}
        else return null;
    }

//   @EventListener(ApplicationReadyEvent.class)
//   public void fillDB(){
//        save(new Patient("Weronika", "Rosatti", "AWQ210321", LocalDate.of(1980, 9, 14 ), "F", "+55 564 000 300", "rosati@gmail.com", "6as123345"));
//        save(new Patient( "Magdalena", "Gorys", "TWM21000021", LocalDate.of(1981, 9, 15 ), "F", "+49 253 923 436", "gorys@gmail.com", "969856"));
//        save(new Patient("Bożena", "Maliszewska", "OTWTH6427", LocalDate.of(1958, 10, 15 ), "F", "+22 125 923 111", "maria234@gmail.com", "162346"));
//        save(new Patient("Agnieszka", "Bargos", "OWTSD210321", LocalDate.of(1963, 6, 14 ), "F", "+78 3214 923 111", "goska235@gmail.com", "864824511"));
//        save(new Patient("Natalia", "Olensko", "PTSR210321", LocalDate.of(1972, 1, 14 ), "F", "+88 111 685 972", "barbara@gmail.com", "e503952"));
//        save(new Patient("Tatiana", "Partyka", "KKS210321", LocalDate.of(1962, 9, 14 ), "F", "+411 777 963 436", "mickiewicz@gmail.com", "5234k6427"));
//   }
}
