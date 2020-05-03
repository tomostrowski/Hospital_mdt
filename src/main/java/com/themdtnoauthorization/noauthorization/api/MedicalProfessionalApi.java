package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.manager.MedicalProfessionalManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medic")
@CrossOrigin
public class MedicalProfessionalApi {

    private MedicalProfessionalManager medicalProfessionalManager;

    public MedicalProfessionalApi(MedicalProfessionalManager medicalProfessionalManager) {
        this.medicalProfessionalManager = medicalProfessionalManager;
    }

    @GetMapping("{id}")
    public MedicalProfessional findById(@PathVariable Long id){
        return medicalProfessionalManager.findById(id).get();
    }

    @GetMapping("/all")
    public Iterable<MedicalProfessional> findAll(){
        return medicalProfessionalManager.findAll();
    }

    @PostMapping("/new")
    public MedicalProfessional addNewMedicalProfessional(@RequestBody MedicalProfessional medicalProfessional){
        return medicalProfessionalManager.save(medicalProfessional);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        medicalProfessionalManager.deleteById(id);
        return ResponseEntity.ok().body("Medical Professional has been deleted.");
    }

    @PatchMapping("{id}/firstName={firstName}")
    public ResponseEntity<String> changeFirstName(@PathVariable Long id, @PathVariable String firstName){
        MedicalProfessional medicalProfessional = medicalProfessionalManager.findById(id).get();
        medicalProfessional.setFirstName(firstName);
        medicalProfessionalManager.save(medicalProfessional);
        return ResponseEntity.ok().body("First name has been changed to "+firstName);
    }

    @PatchMapping("{id}/lastName={lastName}")
    public ResponseEntity<String> changeLastName(@PathVariable Long id, @PathVariable String lastName){
        MedicalProfessional medicalProfessional = medicalProfessionalManager.findById(id).get();
        medicalProfessional.setLastName(lastName);
        medicalProfessionalManager.save(medicalProfessional);
        return ResponseEntity.ok().body("Last name has been changed to "+lastName);
    }

    @PatchMapping("{id}/email={email}")
    public ResponseEntity<String> changeEmail(@PathVariable Long id, @PathVariable String email){
        MedicalProfessional medicalProfessional = medicalProfessionalManager.findById(id).get();
        medicalProfessional.setEmail(email);
        medicalProfessionalManager.save(medicalProfessional);
        return ResponseEntity.ok().body("Email has been changed to "+email);
    }

    @PatchMapping("{id}/mobileNumber={mobileNumber}")
    public ResponseEntity<String> changeMobile(@PathVariable Long id, @PathVariable String mobileNumber){
        MedicalProfessional medicalProfessional = medicalProfessionalManager.findById(id).get();
        medicalProfessional.setMobileNumber(mobileNumber);
        medicalProfessionalManager.save(medicalProfessional);
        return ResponseEntity.ok().body("Mobile number has been changed to "+mobileNumber);
    }

    @PatchMapping("{id}/number={number}")
    public ResponseEntity<String> changeProfessionalNumber(@PathVariable Long id, @PathVariable String number){
        MedicalProfessional medicalProfessional = medicalProfessionalManager.findById(id).get();
        medicalProfessional.setProfessionalNumber(number);
        medicalProfessionalManager.save(medicalProfessional);
        return ResponseEntity.ok().body("Professional number has been changed to "+number);
    }

    @PatchMapping("{id}/specialisation={specialisation}")
    public ResponseEntity<String> changeSpecialisation(@PathVariable Long id, @PathVariable String specialisation){
        MedicalProfessional medicalProfessional = medicalProfessionalManager.findById(id).get();
        medicalProfessional.setSpecialisation(specialisation);
        medicalProfessionalManager.save(medicalProfessional);
        return ResponseEntity.ok().body("Specialisation has been changed to "+specialisation);
    }


}
