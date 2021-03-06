package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.*;
import com.themdtnoauthorization.noauthorization.manager.*;
import com.themdtnoauthorization.noauthorization.model.MdtListModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/disease")
@CrossOrigin
public class DiseaseApi {
    private final DiseaseManager diseaseManager;
    private final MedicalProfessionalManager medicalProfessionalManager;
    private final CancerInfoManager cancerInfoManager;
    private final TreatmentHistoryManager treatmentHistoryManager;
    private MedicalHistoryManager medicalHistoryManager;
    private final MdtManager mdtManager;

    public DiseaseApi(DiseaseManager diseaseManager, MedicalProfessionalManager medicalProfessionalManager, CancerInfoManager cancerInfoManager,
                      TreatmentHistoryManager treatmentHistoryManager, MedicalHistoryManager medicalHistoryManager, MdtManager mdtManager) {
        this.diseaseManager = diseaseManager;
        this.medicalProfessionalManager = medicalProfessionalManager;
        this.cancerInfoManager = cancerInfoManager;
        this.treatmentHistoryManager = treatmentHistoryManager;
        this.medicalHistoryManager = medicalHistoryManager;
        this.mdtManager = mdtManager;
    }

    @GetMapping("/{id}")
    public Optional<Disease> findById(@PathVariable Long id) {
        return diseaseManager.findById(id);
    }


    @GetMapping("/all")
    public Iterable<Disease> findAll() {
        return diseaseManager.findAll();
    }

    @GetMapping("/{id}/mdts")
    public Iterable<MdtListModel> getListForDisease(@PathVariable Long id){
        return mdtManager.getMdtListForDisease(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        diseaseManager.deleteById(id);
        return ResponseEntity.ok().body("Disease has been deleted.");
    }

    @PostMapping("/new")
    public Disease addNew(@RequestBody Disease disease) {
        return  diseaseManager.save(disease);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Disease update) {
        diseaseManager.update(id, update);
    return ResponseEntity.ok("Disease has been updated.");
    }

   @PatchMapping("{id}/name={name}")
    public ResponseEntity<String> changeName(@PathVariable Long id, @PathVariable String name) {
        Disease disease = diseaseManager.findById(id).get();
        disease.setName(name);
        diseaseManager.save(disease);
        return ResponseEntity.ok().body("Name has been changed to " + name);
    }

    @PatchMapping("/{id}/diagnosedBy={medicalProfessionalId}")
    public ResponseEntity<String> setDiagnosedPhysician(@PathVariable Long id, @PathVariable Long medicalProfessionalId) {
        Disease disease = diseaseManager.findById(id).orElseThrow(() -> new RuntimeException("Disease doesn't exist"));
        MedicalProfessional medic = medicalProfessionalManager.findById(medicalProfessionalId)
                .orElseThrow( () -> new RuntimeException("Medical Professional doesn't exist."));
        disease.setDiagnosingPhysician(medic);
        diseaseManager.save(disease);
        return ResponseEntity.ok().body("Diagnosed Physician has been added.");
    }

    @PatchMapping("/{id}/referredBy={medicalProfessionalId}")
    public ResponseEntity<String> setReferringPhysician(@PathVariable Long id, @PathVariable Long medicalProfessionalId) {
        Disease disease = diseaseManager.findById(id).orElseThrow(() -> new RuntimeException("Disease doesn't exist"));
        MedicalProfessional medic = medicalProfessionalManager.findById(medicalProfessionalId)
                .orElseThrow( () -> new RuntimeException("Medical Professional doesn't exist."));
        disease.setReferringPhysician(medic);
        diseaseManager.save(disease);
        return ResponseEntity.ok().body("Referring Physician has been added.");
    }
//
    @PatchMapping("/{id}/setCancerInfo={cancerInfoId}")
    public ResponseEntity<String> setCancerInfo(@PathVariable Long id, @PathVariable Long cancerInfoId) {
        Disease disease = diseaseManager.findById(id).orElseThrow(() -> new RuntimeException("Disease doesn't exist"));
        CancerInfo cancerInfo = cancerInfoManager.findById(cancerInfoId)
                .orElseThrow(()->new RuntimeException("Cancer Info does't exist"));
        disease.setCancerInfo(cancerInfo);
        diseaseManager.save(disease);
        return ResponseEntity.ok().body("Cancer Info has been set.");
    }

    @PatchMapping("/{id}/setTreatmentHistory={treatmentId}")
    public ResponseEntity<String> setTreatmentHistory(@PathVariable Long id, @PathVariable Long treatmentId) {
        Disease disease = diseaseManager.findById(id).orElseThrow(() -> new RuntimeException("Disease doesn't exist"));
        TreatmentHistory treatmentHistory = treatmentHistoryManager.findById(treatmentId)
                .orElseThrow(()->new RuntimeException("Treatment History does't exist"));
        disease.setTreatmentHistory(treatmentHistory);
        diseaseManager.save(disease);
        return ResponseEntity.ok().body("Treatment History has been set.");
    }
//
//    @PatchMapping("/{id}/setMedicalHistory={medicalHistoryId}")
//    public ResponseEntity<String> setMedicaltHistory(@PathVariable Long id, @PathVariable Long medicalHistoryId) {
//        Disease disease = diseaseManager.findById(id).orElseThrow(() -> new RuntimeException("Disease doesn't exist"));
//        MedicalHistory medicalHistory = medicalHistoryManager.findById(medicalHistoryId)
//                .orElseThrow(()->new RuntimeException("Medical History does't exist"));
//        disease.setMedicalHistory(medicalHistory);
//        diseaseManager.save(disease);
//        return ResponseEntity.ok().body("Medical History has been added.");
//    }

    @PatchMapping("/{id}/addMdt={mdtId}")
    public ResponseEntity<String> addMdt(@PathVariable Long id, @PathVariable Long mdtId) {
        Disease disease = diseaseManager.findById(id).orElseThrow(() -> new RuntimeException("Disease doesn't exist"));
        Mdt mdt = mdtManager.findById(mdtId)
                .orElseThrow(()->new RuntimeException("The MDT does't exist"));
        disease.getMdts().add(mdt);
        mdt.setDisease(disease);
        diseaseManager.save(disease);
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("The MDT has been added.");
    }

    @PatchMapping("/{id}/addImaging={imagingId}")
    public ResponseEntity<String> addImaging(@PathVariable Long id, @PathVariable Long imagingId){
        diseaseManager.addImaging(id, imagingId);
        return ResponseEntity.ok().body("Imaging has been set to disease.");
    }
}
