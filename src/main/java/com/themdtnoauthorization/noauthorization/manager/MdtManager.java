package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.MdtRepo;
import com.themdtnoauthorization.noauthorization.entity.Mdt;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MdtManager {

    private MdtRepo mdtRepo;

    public MdtManager(MdtRepo mdtRepo) {
        this.mdtRepo = mdtRepo;
    }

    public Mdt save(Mdt mdt){
        return  mdtRepo.save(mdt);
    }

    public Optional<Mdt> findById(Long id){
        return mdtRepo.findById(id);
    }

    public Iterable<Mdt> findAll(){
        return mdtRepo.findAll();
    }

    public void deleteById(Long id){
        mdtRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Mdt("To jest podsumowanie MDT."));
    }
}
