package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.MedicalHistory;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import com.themdtnoauthorization.noauthorization.manager.DiseaseManager;
import com.themdtnoauthorization.noauthorization.manager.MedicalHistoryManager;
import com.themdtnoauthorization.noauthorization.manager.PatientManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/medicalHistory")
@CrossOrigin
public class MedicalHistoryApi {
    private MedicalHistoryManager medicalHistoryManager;

    public MedicalHistoryApi(MedicalHistoryManager medicalHistoryManager) {
        this.medicalHistoryManager = medicalHistoryManager;
    }

    @GetMapping("/lastId")
    public Optional<Long> findLastId(){
        return medicalHistoryManager.findLastId();
    }

    @GetMapping("/{id}")
    public Optional<MedicalHistory> findById(@PathVariable Long id){
        return medicalHistoryManager.findById(id);
    }

    @GetMapping("/all")
    public List<MedicalHistory> findAll(){
        return medicalHistoryManager.findAll();
    }

    @PostMapping("/new")
    public MedicalHistory addNew(@RequestBody MedicalHistory medicalHistory){
        return medicalHistoryManager.save(medicalHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody MedicalHistory update){
        medicalHistoryManager.update(id, update);
        return ResponseEntity.ok("Medical History has been updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        MedicalHistory medicalHistory= medicalHistoryManager.findById(id).get();
        medicalHistoryManager.deleteById(medicalHistory.getId());
        return ResponseEntity.ok().body("Medical History has been deleted.");
    }

    @PatchMapping("/{id}/familyHistory={familyHistory}")
    public ResponseEntity<String> changeFamilyHistory(@PathVariable Long id, @PathVariable String familyHistory){
        MedicalHistory medicalHistory= medicalHistoryManager.findById(id).get();
        medicalHistory.setFamilyHistory(familyHistory);
        medicalHistoryManager.save(medicalHistory);
        return ResponseEntity.ok().body("Family History has been changed.");
    }

    @PatchMapping("/{id}/medication={medication")
    public ResponseEntity<String> changeMedication(@PathVariable Long id, @PathVariable String medication){
        MedicalHistory medicalHistory= medicalHistoryManager.findById(id).get();
        medicalHistory.setMedication(medication);
        medicalHistoryManager.save(medicalHistory);
        return ResponseEntity.ok().body("Medication History has been changed.");
    }

    @PatchMapping("/{id}/allergies={allergies}")
    public ResponseEntity<String> changeAllergies(@PathVariable Long id, @PathVariable String allergies){
        MedicalHistory medicalHistory= medicalHistoryManager.findById(id).get();
        medicalHistory.setAllergies(allergies);
        medicalHistoryManager.save(medicalHistory);
        return ResponseEntity.ok().body("Allergies has been changed.");
    }

    @PatchMapping("/{id}/performanceStatus={performanceStatus}")
    public ResponseEntity<String> changePerformanceStatus(@PathVariable Long id, @PathVariable String performanceStatus){
        MedicalHistory medicalHistory= medicalHistoryManager.findById(id).get();
        medicalHistory.setPerformanceStatus(performanceStatus);
        medicalHistoryManager.save(medicalHistory);
        return ResponseEntity.ok().body("Performance status has been changed.");
    }
}
