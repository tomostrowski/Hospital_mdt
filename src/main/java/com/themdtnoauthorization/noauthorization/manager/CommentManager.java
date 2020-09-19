package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.CommentRepo;
import com.themdtnoauthorization.noauthorization.dao.MedicalProfessionalRepo;
import com.themdtnoauthorization.noauthorization.entity.Comment;
import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.model.CommentModel;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentManager {
    private CommentRepo commentRepo;
    private MedicalProfessionalRepo medicalProfessionalRepo;

    public CommentManager(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment save(Comment comment){
        return commentRepo.save(comment);
    }

    public Optional<Comment> findById(Long id){
        return commentRepo.findById(id);
    }

    public Iterable<Comment> findAll(){
        return commentRepo.findAll();
    }

    public void deleteById(Long id){
        commentRepo.deleteById(id);
    }

    public void changeCommentText(Long id, String text) {
        Comment comment = findById(id).orElseThrow(()-> new RuntimeException("Comment does not exist."));
        comment.setText(text);
        comment.setWasEdited("true"); //zamień ta booleanowaskie true
        save(comment);
    }

    public Iterable<CommentModel> list() {
        List<Comment> commentList = commentRepo.findAll();
        if(commentList.size()>0){
            Set<CommentModel> commentModelSet = new LinkedHashSet<>();
            for (Comment comment:commentList){
                CommentModel model = new CommentModel();
                model.setId(comment.getId());
                model.setText(comment.getText());
                model.setDate(comment.getDate());
                model.setWasEdited(comment.getWasEdited());
                if (comment.getAuthor() != null)
                model.setAuthor(comment.getAuthor().getName());
                commentModelSet.add(model);
            }
            return commentModelSet;
        }
        else return new LinkedHashSet<>();
    }


//    public void setAuthor(Long id, Long authorId) {
//        Comment comment = commentRepo.findById(id).orElseThrow(()->new RuntimeException("Comment does not exist."));
//        MedicalProfessional author = medicalProfessionalRepo.findById(authorId).orElseThrow(()->new RuntimeException("Author does not exist."));
////        comment.setAuthor(author);
////        commentRepo.save(comment);
//    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB(){
//        save(new Comment("Cześć to jest komentarz. Uważam, że ten pacjent jest chory i trzeba go szybko operować."));
//    }
}
