package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.CommentRepo;
import com.themdtnoauthorization.noauthorization.entity.Comment;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentManager {
    private CommentRepo commentRepo;

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

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Comment("Cześć to jest komentarz. Uważam, że ten pacjent jest chory i trzeba go szybko operować."));
    }
}
