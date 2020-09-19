package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.TreatmentHistory;
import com.themdtnoauthorization.noauthorization.manager.TreatmentHistoryManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/treatment")
@CrossOrigin
public class TreatmentHistoryApi {
    private TreatmentHistoryManager treatmentHistoryManager;

    public TreatmentHistoryApi(TreatmentHistoryManager treatmentHistoryManager) {
        this.treatmentHistoryManager = treatmentHistoryManager;
    }

    @GetMapping("/{id}}")
    public Optional<TreatmentHistory> findById(@PathVariable Long id){
        return treatmentHistoryManager.findById(id);
    }

    @GetMapping("/all")
    public Iterable<TreatmentHistory> findAll(){
        return treatmentHistoryManager.findAll();
    }

    @PostMapping("/new")
    public TreatmentHistory addNew(@RequestBody TreatmentHistory treatmentHistory){
        return treatmentHistoryManager.save(treatmentHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody TreatmentHistory update){
        treatmentHistoryManager.update(id, update);
        return ResponseEntity.ok("Treatment History has been updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        treatmentHistoryManager.deleteById(id);
        return ResponseEntity.ok().body("Treatment history has benn deleted.");
    }

    @PatchMapping("/{id}/biopsy")
    public ResponseEntity<String> changeBiopsy(@PathVariable Long id, @RequestBody String biopsy){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setBiopsy(biopsy);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Biopsy has been changed.");
    }

    @PatchMapping("/{id}/surgery")
    public ResponseEntity<String> changeSurgery(@PathVariable Long id, @RequestBody String surgery){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setSurgery(surgery);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Surgery has been changed.");
    }

    @PatchMapping("/{id}/radiotherapy")
    public ResponseEntity<String> changeRadiotherapy(@PathVariable Long id, @RequestBody String radiotherapy){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setRadiotherapy(radiotherapy);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Radiotherapy has been changed.");
    }

    @PatchMapping("/{id}/endocrine")
    public ResponseEntity<String> changeEndocrineTreatment(@PathVariable Long id, @RequestBody String endocrine){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setEndocrineTreatment(endocrine);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Endocrine Treatment has been changed.");
    }

    @PatchMapping("/{id}/chemotherapy")
    public ResponseEntity<String> changeChemotherapy(@PathVariable Long id, @RequestBody String chemotherapy){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setChemotherapy(chemotherapy);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Chemotherapy has been changed.");
    }

    @PatchMapping("/{id}/immunotherapy")
    public ResponseEntity<String> changeImmunotherapy(@PathVariable Long id, @RequestBody String immunotherapy){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setImmunotherapy(immunotherapy);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Immunotherapy has been changed.");
    }

    @PatchMapping("/{id}/physiotherapy")
    public ResponseEntity<String> changePhysiotherapy(@PathVariable Long id, @RequestBody String physiotherapy){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setPhysiotherapy(physiotherapy);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Physiotherapy has been changed.");
    }

    @PatchMapping("/{id}/other")
    public ResponseEntity<String> changeOther(@PathVariable Long id, @RequestBody String other){
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(id).get();
        treatmentHistory.setOther(other);
        treatmentHistoryManager.save(treatmentHistory);
        return ResponseEntity.ok().body("Other has been changed.");
    }
}
