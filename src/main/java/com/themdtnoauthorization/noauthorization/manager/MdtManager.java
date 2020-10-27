package com.themdtnoauthorization.noauthorization.manager;

import com.fasterxml.jackson.databind.node.TextNode;
import com.themdtnoauthorization.noauthorization.dao.MdtRepo;
import com.themdtnoauthorization.noauthorization.entity.Comment;
import com.themdtnoauthorization.noauthorization.entity.Mdt;
import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.model.CommentModel;
import com.themdtnoauthorization.noauthorization.model.MedicalProfessionalModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;


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

    public Set<CommentModel> getAllCommentsById(Long id) {
        Mdt mdt = mdtRepo.findById(id).orElseThrow(()->new RuntimeException("Mdt does not exist."));
        Set<Comment> commentSet = new LinkedHashSet<>(mdt.getComments());
        Set<CommentModel> commentModelSet = new LinkedHashSet<>();
        if (commentSet.size() > 0) {
            for (Comment comment : commentSet) {
                CommentModel model = new CommentModel();
                model.setId(comment.getId());
                model.setText(comment.getText());
                if(comment.getAuthor() != null)
                model.setAuthor(comment.getAuthor().getFirstName()+" "+comment.getAuthor().getLastName());
                model.setDateOfEditing(comment.getDateOfEditing());
                commentModelSet.add(model);
            }
            return commentModelSet;
        } else return new LinkedHashSet<>();
    }

    public MedicalProfessionalModel getAuthor(Comment comment){
        MedicalProfessional author = comment.getAuthor();
        if (author != null){
            MedicalProfessionalModel model = new MedicalProfessionalModel();
            model.setId(author.getId());
            model.setFirstName(author.getFirstName());
            model.setLastName(author.getLastName());
            return model;
        } else return null;
    }

    public ResponseEntity<String> setEndDate(Long id, LocalDate endDate) {
        Mdt mdt =findById(id).orElseThrow(()-> new RuntimeException("Mdt does not exist."));
        mdt.setEndDate(endDate);
        mdtRepo.save(mdt);
        return ResponseEntity.ok().body("End Date of the MDT has been set to "+endDate);
    }

    public ResponseEntity<String> setSummary(Long id, TextNode summary) {
        Mdt mdt =findById(id).orElseThrow(()-> new RuntimeException("Mdt does not exist."));
        mdt.setSummary(summary.asText());
        mdtRepo.save(mdt);
        return ResponseEntity.ok().body("Summary has been set.");
    }

    public ResponseEntity<String> setSummaryAdnEndDateNow(Long id, TextNode nodeSummary) {
        Mdt mdt =findById(id).orElseThrow(()-> new RuntimeException("Mdt does not exist."));
        mdt.setSummary(nodeSummary.asText());
        mdt.setEndDate(LocalDate.now());
        mdtRepo.save(mdt);
        return ResponseEntity.ok().body("Summary has been set and the MDT has been closed today.");
    }

    public ResponseEntity<String> setReviewDate(Long id, LocalDate reviewDate) {
        Mdt mdt =findById(id).orElseThrow(()-> new RuntimeException("Mdt does not exist."));
        mdt.setReviewDate(reviewDate);
        mdtRepo.save(mdt);
        return ResponseEntity.ok().body("End Date of the MDT has been set to "+reviewDate);
    }

//    @EventListener(ApplicationReadyEvent.class)
//     void fillDB(){
//        save(new Mdt("To jest podsumowanie MDT."));
//    }
}
