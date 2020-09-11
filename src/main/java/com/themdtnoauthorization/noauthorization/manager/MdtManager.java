package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.MdtRepo;
import com.themdtnoauthorization.noauthorization.entity.Comment;
import com.themdtnoauthorization.noauthorization.entity.Mdt;
import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.model.CommentModel;
import com.themdtnoauthorization.noauthorization.model.MedicalProfessionalModel;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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
        Optional<Mdt> mdt = mdtRepo.findById(id);
        Set<Comment> commentSet = mdt.orElseThrow(() -> new RuntimeException("This MDT does not exist.")).getComments();
        ;
        Set<CommentModel> commentModelSet = new LinkedHashSet<>();
        if (commentSet.size() > 0) {
            for (Comment comment : commentSet) {
                CommentModel model = new CommentModel();
                model.setId(comment.getId());
                model.setText(comment.getText());
                model.setAuthor(getAuthor(comment));
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

//    @EventListener(ApplicationReadyEvent.class)
//     void fillDB(){
//        save(new Mdt("To jest podsumowanie MDT."));
//    }
}
