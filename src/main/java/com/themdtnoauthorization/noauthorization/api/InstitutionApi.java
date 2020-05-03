package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.Institution;
import com.themdtnoauthorization.noauthorization.manager.InstitutionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/institution")
@CrossOrigin
public class InstitutionApi {
    private InstitutionManager institutionManager;

    public InstitutionApi(InstitutionManager institutionManager) {
        this.institutionManager = institutionManager;
    }

    @GetMapping("/{id}")
    public Optional<Institution> findById(@PathVariable Long id){
        return institutionManager.findById(id);
    }

    @GetMapping("/all")
    public Iterable<Institution> findAll(){
        return institutionManager.findAll();
    }

    @PostMapping("/new")
    public Institution addNew(Institution institution){
        return institutionManager.save(institution);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        Institution institution = institutionManager.findById(id).get();
        institutionManager.deleteById(id);
        return ResponseEntity.ok().body("Institution with name "+ institution.getName()+" has been deleted.");
    }

    @PatchMapping("/{id}/name={name}")
    public ResponseEntity<String> changeName(@PathVariable Long id, @PathVariable String name){
        Institution institution = institutionManager.findById(id).get();
        institution.setName(name);
        institutionManager.save(institution);
        return ResponseEntity.ok().body("Name of this institution has been changed to "+name);
    }
}
