package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.CancerInfoRepo;
import com.themdtnoauthorization.noauthorization.entity.CancerInfo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CancerInfoManager {
    private CancerInfoRepo cancerInfoRepo;

    public CancerInfoManager(CancerInfoRepo cancerInfoRepo) {
        this.cancerInfoRepo = cancerInfoRepo;
    }

    public CancerInfo save(CancerInfo cancerInfo){
        return cancerInfoRepo.save(cancerInfo);
    }

    public CancerInfo update(Long id, CancerInfo updatedCancerInfo) {
        updatedCancerInfo.setId(id);
        return cancerInfoRepo.save(updatedCancerInfo);
    }

    public Optional<CancerInfo> findById(Long id){
        return cancerInfoRepo.findById(id);
    }

    public Iterable<CancerInfo> findAll(){
        return cancerInfoRepo.findAll();
    }

    public void  deleteById(Long id){
        cancerInfoRepo.deleteById(id);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDb(){
//        save(new CancerInfo("palpable 3cm mass UOQ and axillary nodes, Imaging: MMG Right breast normal, left breast 27mm density with microcalcifications suspicious for malignancy BIRADS 4c/5"));
//    }
}
