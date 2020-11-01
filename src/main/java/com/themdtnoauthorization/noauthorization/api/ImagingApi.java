package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.Imaging;
import com.themdtnoauthorization.noauthorization.entity.Patient;
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

    @PostMapping("/new")
    public Imaging addNew(@RequestBody Imaging imaging){
        return imagingManager.save(imaging);
    }

    @GetMapping("/{id}")
    public Imaging findById(@PathVariable Long id){
        return imagingManager.findById(id).orElseThrow(()-> new RuntimeException("Imaging does not exist."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateImaging(@RequestBody Imaging imagingToUpdate, @PathVariable Long id) {
        imagingManager.update(id, imagingToUpdate);
        return ResponseEntity.ok().body("Imaging has been updated.");
    }
}
