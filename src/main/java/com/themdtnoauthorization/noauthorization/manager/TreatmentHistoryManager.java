package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.TreatmentHistoryRepo;
import com.themdtnoauthorization.noauthorization.entity.TreatmentHistory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreatmentHistoryManager {
    private TreatmentHistoryRepo treatmentHistoryRepo;

    public TreatmentHistoryManager(TreatmentHistoryRepo treatmentHistoryRepo) {
        this.treatmentHistoryRepo = treatmentHistoryRepo;
    }

    public TreatmentHistory save(TreatmentHistory treatmentHistory){
        return treatmentHistoryRepo.save(treatmentHistory);
    }

    public Optional<TreatmentHistory> findById(Long id){
        return treatmentHistoryRepo.findById(id);
    }

    public Iterable<TreatmentHistory> findAll(){
        return treatmentHistoryRepo.findAll();
    }

    public void deleteById(Long id){
        treatmentHistoryRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new TreatmentHistory());
    }
}
