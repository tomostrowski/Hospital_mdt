package com.themdtnoauthorization.noauthorization.api;

import com.fasterxml.jackson.databind.node.TextNode;
import com.themdtnoauthorization.noauthorization.entity.Comment;
import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Mdt;
import com.themdtnoauthorization.noauthorization.manager.CommentManager;
import com.themdtnoauthorization.noauthorization.manager.DiseaseManager;
import com.themdtnoauthorization.noauthorization.manager.InstitutionManager;
import com.themdtnoauthorization.noauthorization.manager.MdtManager;
import com.themdtnoauthorization.noauthorization.model.CommentModel;
import com.themdtnoauthorization.noauthorization.model.MdtListModel;
import com.themdtnoauthorization.noauthorization.model.PatientListModel;
import com.themdtnoauthorization.noauthorization.model.SummarySingleModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/mdt")
@CrossOrigin
public class MdtApi {

    private MdtManager mdtManager;
    private DiseaseManager diseaseManager;
    private InstitutionManager institutionManager;
    private CommentManager commentManager;

    public MdtApi(MdtManager mdtManager, DiseaseManager diseaseManager, InstitutionManager institutionManager, CommentManager commentManager) {
        this.mdtManager = mdtManager;
        this.diseaseManager = diseaseManager;
        this.institutionManager = institutionManager;
        this.commentManager = commentManager;
    }

    @GetMapping("/{id}")
    public Mdt findById(@PathVariable Long id){
        return mdtManager.findById(id).orElseThrow(()-> new RuntimeException("MDT does not exist."));
    }

    @GetMapping("/all")
    public Iterable<MdtListModel> findAll(){
        return mdtManager.getMdtList();
    }

    @GetMapping("/list")
    public Iterable<MdtListModel> listOfAll(){
        return mdtManager.getMdtList();
    }

    @GetMapping("{id}/comments")
    public Set<CommentModel> getAllCommentsById(@PathVariable Long id){
        return mdtManager.getAllCommentsById(id);
    }

    @PostMapping("/new")
    public Mdt addNew(@RequestBody Mdt mdt){
        return mdtManager.save(mdt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Mdt update){
        Mdt mdt = mdtManager.findById(id).orElseThrow(()-> new RuntimeException("Mdt not found."));
        update.setId(mdt.getId());
        update.setDisease(mdt.getDisease());
        update.setComments(mdt.getComments());
        mdtManager.save(update);
        return ResponseEntity.ok().body("MDT has been updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        mdtManager.deleteById(id);
        return ResponseEntity.ok().body("MDT has been deleted.");
    }

    @PatchMapping("/{id}/setDisease={diseaseId}")
    public ResponseEntity<String> setDisease(@PathVariable Long id, @PathVariable Long diseaseId){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        Disease disease = diseaseManager.findById(diseaseId)
                .orElseThrow(()-> new RuntimeException("Disease does not exist."));
        mdt.setDisease(disease);
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("Disease has been set.");
    }

        @PatchMapping("/{id}/location={location}")
    public ResponseEntity<String> setLocation(@PathVariable Long id, @PathVariable String location){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        mdt.setLocationOfTreatment(location);
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("Location of treatment has been set.");
    }

//    @PatchMapping("/{id}/location={institutionId}")
//    public ResponseEntity<String> setLocation(@PathVariable Long id, @PathVariable Long institutionId ){
//        Mdt mdt = mdtManager.findById(id)
//                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
//        Institution institution = institutionManager.findById(institutionId)
//                .orElseThrow(()-> new RuntimeException("Institution does not exist."));
//        mdt.setLocationOfTreatment(institution);
//        mdtManager.save(mdt);
//        return ResponseEntity.ok().body("Location of treatment has been set.");
//    }

    @PatchMapping("/{id}/affiliation={institution}")
    public ResponseEntity<String> setAffiliation(@PathVariable Long id, @PathVariable String institution ){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        mdt.setAffiliation(institution);
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("Affiliation of treatment has been set.");
    }
//    @PatchMapping("/{id}/affiliation={institutionId}")
//    public ResponseEntity<String> setAffiliation(@PathVariable Long id, @PathVariable Long institutionId ){
//        Mdt mdt = mdtManager.findById(id)
//                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
//        Institution institution = institutionManager.findById(institutionId)
//                .orElseThrow(()-> new RuntimeException("Institution does not exist."));
//        mdt.setAffiliation(institution);
//        mdtManager.save(mdt);
//        return ResponseEntity.ok().body("Affiliation of treatment has been set.");
//    }

    @PatchMapping("/{id}/addComment={commentId}")
    public ResponseEntity<String> addComment(@PathVariable Long id, @PathVariable Long commentId){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        Comment comment = commentManager.findById(commentId)
                .orElseThrow(()-> new RuntimeException("Comment does not exist."));
        mdt.getComments().add(comment);
        mdtManager.save(mdt);
        comment.setMdt(mdt);
        commentManager.save(comment);
        return ResponseEntity.ok().body("Comment has been added.");
    }

    @PatchMapping("/{id}/isOpen={status}")
    public ResponseEntity<String> addComment(@PathVariable Long id, @PathVariable String status){
        Mdt mdt = mdtManager.findById(id)
                .orElseThrow(()-> new RuntimeException("MDT does not exist."));
        mdtManager.save(mdt);
        return ResponseEntity.ok().body("IsOpen has been changed.");
    }

    @PatchMapping("/{id}/endDate={endDate}")
    public ResponseEntity<String> setEndDate(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return  mdtManager.setEndDate(id, endDate);
    }

    @PatchMapping("{id}/setSummary")
    public ResponseEntity<String> setSummary(@PathVariable Long id, @RequestBody TextNode summary){
        return mdtManager.setSummary(id, summary);
    }

    @PatchMapping("/{id}/summaryAndEndDateNow")
    public ResponseEntity<String> setSummaryAndEndDateNow(@PathVariable Long id, @RequestBody SummarySingleModel summary){
        return mdtManager.setSummaryAdnEndDateNow(id, summary);
    }

    @PatchMapping("/{id}/reviewDate={reviewDate}")
    public ResponseEntity<String> setReviewDate(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reviewDate) {
        return  mdtManager.setEndDate(id, reviewDate);
    }


}
