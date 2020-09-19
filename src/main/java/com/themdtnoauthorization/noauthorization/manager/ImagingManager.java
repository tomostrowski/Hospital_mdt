package com.themdtnoauthorization.noauthorization.manager;


import com.themdtnoauthorization.noauthorization.dao.ImagingRepo;
import com.themdtnoauthorization.noauthorization.entity.Imaging;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImagingManager {

    private final ImagingRepo imagingRepo;

    public ImagingManager(ImagingRepo imagingRepo) {
        this.imagingRepo = imagingRepo;
    }

    public Imaging save(Imaging imaging){
        return imagingRepo.save(imaging);
    }

    public Optional<Imaging> findById(Long id){
        return imagingRepo.findById(id);
    }

    public void delete(Imaging imaging){
        imagingRepo.delete(imaging);
    }
}
