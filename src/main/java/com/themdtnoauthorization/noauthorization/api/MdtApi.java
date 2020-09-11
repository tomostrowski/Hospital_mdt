package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Institution;
import com.themdtnoauthorization.noauthorization.entity.Mdt;
import com.themdtnoauthorization.noauthorization.manager.DiseaseManager;
import com.themdtnoauthorization.noauthorization.manager.InstitutionManager;
import com.themdtnoauthorization.noauthorization.manager.MdtManager;
import com.themdtnoauthorization.noauthorization.model.CommentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/mdt")
@CrossOrigin
public class MdtApi {

    private MdtManager mdtManager;
    private DiseaseManager diseaseManager;
    private InstitutionManager institutionManager;

    public MdtApi(MdtManager mdtManager, DiseaseManager diseaseManager, InstitutionManager institutionManager) {
        this.mdtManager = mdtManager;
        this.diseaseManager = diseaseManager;
        this.institutionManager = institutionManager;
    }

    @GetMapping("/{id}")
    public Mdt findById(@PathVariable Long id){
        return mdtManager.findById(id).orElseThrow(()-> new RuntimeException("MDT does not exist."));
    }

    @GetMapping("/all")
    public Iterable<Mdt> findAll(){
        return mdtManager.findAll();
    }

    @GetMapping("{id}/comments")
    public Set<CommentModel> getAllCommentsById(@PathVariable Long id){
        return mdtManager.getAllCommentsById(id);
    }

    @PostMapping("/new")
    public Mdt addNew(@RequestBody Mdt mdt){
        return mdtManager.save(mdt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        mdtManager.deleteById(id);
        return ResponseEntity.ok().body("MDT has been deleted.");
    }

    @PatchMapping("/{id}/setDisease={diseaseId}")
    public ResponseEntity<String> setDisease(@PathVariable Long id, @PathVariable Long diseaseId){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        Disease disease = diseaseManager.findById(diseaseId)
                .orElseThrow(()-> new RuntimeException("Disease does not exist."));
        mdt.setDisease(disease);
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("Disease has been set.");
    }

    @PatchMapping("/{id}/location={institutionId}")
    public ResponseEntity<String> setLocation(@PathVariable Long id, @PathVariable Long institutionId ){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        Institution institution = institutionManager.findById(institutionId)
                .orElseThrow(()-> new RuntimeException("Institution does not exist."));
        mdt.setLocationOfTreatment(institution);
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("Location of treatment has been set.");
    }

    @PatchMapping("/{id}/affiliation={institutionId}")
    public ResponseEntity<String> setAffiliation(@PathVariable Long id, @PathVariable Long institutionId ){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        Institution institution = institutionManager.findById(institutionId)
                .orElseThrow(()-> new RuntimeException("Institution does not exist."));
        mdt.setAffiliation(institution);
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("Affiliation of treatment has been set.");
    }


}
