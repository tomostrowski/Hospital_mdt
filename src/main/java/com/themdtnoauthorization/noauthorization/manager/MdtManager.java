package com.themdtnoauthorization.noauthorization.manager;

import com.fasterxml.jackson.databind.node.TextNode;
import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.MdtRepo;
import com.themdtnoauthorization.noauthorization.entity.*;
import com.themdtnoauthorization.noauthorization.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class MdtManager {

    private final MdtRepo mdtRepo;
    private DiseaseRepo diseaseRepo;

    public MdtManager(MdtRepo mdtRepo, DiseaseRepo diseaseRepo) {
        this.mdtRepo = mdtRepo;
        this.diseaseRepo = diseaseRepo;
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

//    public MedicalProfessionalModel getAuthor(Comment comment){
//        User author = comment.getAuthor();
//        if (author != null){
//            MedicalProfessionalModel model = new MedicalProfessionalModel();
//            model.setId(author.getId());
//            model.setFirstName(author.getFirstName());
//            model.setLastName(author.getLastName());
//            return model;
//        } else return null;
//    }

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

    public ResponseEntity<String> setSummaryAdnEndDateNow(Long id, SummarySingleModel summary) {
        Mdt mdt =findById(id).orElseThrow(()-> new RuntimeException("Mdt does not exist."));
        mdt.setSummary(summary.getSummary());
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

    public Iterable<MdtListModel> getMdtList() {
            Set<Mdt> mdtSet = new LinkedHashSet<>(mdtRepo.findAll(Sort.by(Sort.Direction.ASC, "endDate")));
            if (mdtSet.size() > 0) {
                Set<MdtListModel> mdtListModels = new LinkedHashSet<>();
                for (Mdt mdt : mdtSet) {
                    MdtListModel model = new MdtListModel();
                    model.setId(mdt.getId());
                    model.setSummary(mdt.getSummary());
                    model.setAdditionalComments(mdt.getAdditionalComments());
                    model.setAffiliation(mdt.getAffiliation());
                    model.setDateOfReferralForMDT(mdt.getDateOfReferralForMDT());
                    model.setEndDate(mdt.getEndDate());
                    model.setLocationOfTreatment(mdt.getLocationOfTreatment());
                    model.setReviewDate(mdt.getReviewDate());
                    model.setStartDate(mdt.getStartDate());
                    if(mdt.getDisease() != null) model.setDiseaseName(mdt.getDisease().getName());
                    if(mdt.getDisease() != null) model.setPatientName(mdt.getDisease().getPatient().getGivenName()+" "+mdt.getDisease().getPatient().getSurname());
                    mdtListModels.add(model);
                }
                return mdtListModels;
            } else return new LinkedHashSet<MdtListModel>();
        }

    public Iterable<MdtListModel> getMdtListForDisease(Long id) {

        Disease disease = diseaseRepo.findById(id).orElseThrow(() -> new RuntimeException("Disease not found."));
        Set<Mdt> mdtSet = new LinkedHashSet<>(mdtRepo.findMdtsByDisease(disease));
        if (mdtSet.size() > 0) {
            Set<MdtListModel> mdtListModels = new LinkedHashSet<>();
            for (Mdt mdt : mdtSet) {
                MdtListModel model = new MdtListModel();
                model.setId(mdt.getId());
                model.setSummary(mdt.getSummary());
                model.setAdditionalComments(mdt.getAdditionalComments());
                model.setAffiliation(mdt.getAffiliation());
                model.setDateOfReferralForMDT(mdt.getDateOfReferralForMDT());
                model.setEndDate(mdt.getEndDate());
                model.setLocationOfTreatment(mdt.getLocationOfTreatment());
                model.setReviewDate(mdt.getReviewDate());
                model.setStartDate(mdt.getStartDate());
                model.setComments(getCommentsForMdt(mdt));
                mdtListModels.add(model);
            }
            return mdtListModels;
        } else return new LinkedHashSet<MdtListModel>();

    }

    public Set<CommentModel> getCommentsForMdt(Mdt mdt) {
        if (mdt.getComments().size() > 0) {
            Set<CommentModel> commentListModels = new LinkedHashSet<>();
            for (Comment comment : mdt.getComments()) {
                CommentModel model = new CommentModel();
                model.setId(comment.getId());
                model.setDate(comment.getDate());
                model.setDateOfEditing(comment.getDateOfEditing());
                model.setAuthor(comment.getAuthor().getName());
                model.setText(comment.getText());
                commentListModels.add(model);
            }
            return commentListModels;
        } else return new LinkedHashSet<CommentModel>();

    }

//    @EventListener(ApplicationReadyEvent.class)
//     void fillDB(){
//        save(new Mdt("To jest podsumowanie MDT."));
//    }
}
