package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.MedicalProfessionalRepo;
import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalProfessionalManager {

    private MedicalProfessionalRepo medicalProfessionalRepo;

    public MedicalProfessionalManager(MedicalProfessionalRepo medicalProfessionalRepo) {
        this.medicalProfessionalRepo = medicalProfessionalRepo;
    }

    public MedicalProfessional save(MedicalProfessional medicalProfessional){
        return medicalProfessionalRepo.save(medicalProfessional);
    }

    public Optional<MedicalProfessional> findById(Long id){
        return medicalProfessionalRepo.findById(id);
    }

    public Iterable<MedicalProfessional> findAll(){
        return medicalProfessionalRepo.findAll();
    }

    public void deleteById(Long id){
        medicalProfessionalRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new MedicalProfessional("Marek Mariusz","Ostrowski"));
        save(new MedicalProfessional("Adam","Barczewski" ));
        save(new MedicalProfessional("Michał","Szary" ));
    }
}
