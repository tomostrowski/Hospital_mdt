package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.Imaging;
import com.themdtnoauthorization.noauthorization.manager.ImagingManager;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imaging")
@CrossOrigin
public class ImagingApi {

    private final ImagingManager imagingManager;

    public ImagingApi(ImagingManager imagingManager) {
        this.imagingManager = imagingManager;
    }

    @GetMapping("/new")
    public ResponseEntity<String> addNew(@RequestBody Imaging imaging){
        imagingManager.save(imaging);
        return ResponseEntity.ok().body("New imaging has been added.");
    }

    @GetMapping("/{id}")
    public Imaging findById(Long id){
        return imagingManager.findById(id).orElseThrow(()-> new RuntimeException("Imaging does not exist."));
    }
}
