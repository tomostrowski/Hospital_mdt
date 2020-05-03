package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.InstitutionRepo;
import com.themdtnoauthorization.noauthorization.entity.Institution;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstitutionManager {
    private InstitutionRepo institutionRepo;

    public InstitutionManager(InstitutionRepo institutionRepo) {
        this.institutionRepo = institutionRepo;
    }

    public Institution save(Institution institution){
        return institutionRepo.save(institution);
    }

    public Optional<Institution> findById(Long id){
        return institutionRepo.findById(id);
    }

    public Iterable<Institution> findAll(){
        return institutionRepo.findAll();
    }

    public void deleteById(Long id){
        institutionRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        save(new Institution("Shah Hospital"));
        save(new Institution("Kenia Public Hospital"));
    }

}
