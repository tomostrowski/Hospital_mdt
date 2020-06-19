package com.themdtnoauthorization.noauthorization.api;


import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import com.themdtnoauthorization.noauthorization.manager.DiseaseManager;
import com.themdtnoauthorization.noauthorization.manager.PatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/patient")
@CrossOrigin
public class PatientApi {

    private PatientManager patientManager;
    private DiseaseManager diseaseManager;

    @Autowired
    public PatientApi(PatientManager patientManager, DiseaseManager diseaseManager) {
        this.patientManager = patientManager;
        this.diseaseManager = diseaseManager;
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id){
        return patientManager.findById(id);
    }

    @GetMapping("/all")
    public List<Patient> getAllPatients(){
        return patientManager.findAll();
    }

    @GetMapping("{id}/all")
    public Set<Disease> getAllPatientsDiseases(@PathVariable Long id){
        return patientManager.findAllDiseasesByPatientId(id);
    }

    @PostMapping("/new")
    public Patient addNewPatient(@RequestBody Patient patient){
        return patientManager.save(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Long id){
        Patient patient= patientManager.findById(id).get();
        patient.getGivenName();

        patientManager.deleteById(id);
        return ResponseEntity.ok().body("Patient "+patient.getGivenName()+" "+patient.getSurname()+" has been deleted.");
    }

    @PatchMapping("/{id}/name={name}")
    public ResponseEntity<String> changeName(@PathVariable Long id, @PathVariable String name){
        Patient patient= patientManager.findById(id).get();
        patient.setGivenName(name);
        patientManager.save(patient);
        return ResponseEntity.ok().body("Name has been changed to "+ name+".");
    }

    @PatchMapping("/{id}/surname={surname}")
    public ResponseEntity<String> changeSurname(@PathVariable Long id, @PathVariable String surname){
        Patient patient= patientManager.findById(id).get();
        patient.setSurname(surname);
        patientManager.save(patient);
        return ResponseEntity.ok().body("Surname has been changed to "+ surname+".");
    }

    @PatchMapping("/{id}/email={email}")
    public ResponseEntity<String> changeEmail(@PathVariable Long id, @PathVariable String email){
        Patient patient= patientManager.findById(id).get();
        patient.setEmail(email);
        patientManager.save(patient);
        return ResponseEntity.ok().body("Email has been changed to "+ email+".");
    }

    @PatchMapping("/{id}/idNumber={idNumber}")
    public ResponseEntity<String> changeIdNumber(@PathVariable Long id, @PathVariable String idNumber){
        Patient patient= patientManager.findById(id).get();
        patient.setIdNumber(idNumber);
        patientManager.save(patient);
        return ResponseEntity.ok().body("Id number has been changed to "+ idNumber+".");
    }

    @PatchMapping("/{id}/addDisease={diseaseId}")
    public ResponseEntity<String> addDisease(@PathVariable Long id, @PathVariable Long diseaseId){
        Patient patient= patientManager.findById(id).get();
        Disease disease = diseaseManager.findById(diseaseId).get();
        patient.getDiseases().add(disease);
        patientManager.save(patient);
//        disease.setPatient(patient);
//        diseaseManager.save(disease);
        return ResponseEntity.ok().body("Disease has been added to patient "+ patient.getGivenName()+" "+patient.getSurname()+".");
    }

}
