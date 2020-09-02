package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.MedicalHistoryRepo;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.MedicalHistory;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryManager {
    private MedicalHistoryRepo medicalHistoryRepo;

    public MedicalHistoryManager(MedicalHistoryRepo medicalHistoryRepo) {
        this.medicalHistoryRepo = medicalHistoryRepo;
    }

    public MedicalHistory save(MedicalHistory medicalHistory) {
        return medicalHistoryRepo.save(medicalHistory);
    }

    public MedicalHistory update(Long id, MedicalHistory update){
        update.setId(id);
       return medicalHistoryRepo.save(update);
    }

    public Optional<MedicalHistory> findById(Long id){
        return medicalHistoryRepo.findById(id);
    }

    public List<MedicalHistory> findAll(){
        return medicalHistoryRepo.findAll();
    }

    public void deleteById(Long id){
        medicalHistoryRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        save(new MedicalHistory("mother breast cancer at 53yo, mother’s sister breast cancer at 49", "none", "none", "fdaf"));

    }
}
