package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.Comment;
import com.themdtnoauthorization.noauthorization.manager.CommentManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class CommentApi {
    private CommentManager commentManager;

    public CommentApi(CommentManager commentManager) {
        this.commentManager = commentManager;
    }

    @GetMapping("/{id}")
    public Optional<Comment> findById(@PathVariable Long id){
        return  commentManager.findById(id);
    }

    @GetMapping("/all")
    public Iterable<Comment> findAll(){
        return commentManager.findAll();
    }

    @PostMapping("/new")
    public Comment addNew(Comment comment){
        return commentManager.save(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        commentManager.deleteById(id);
        return ResponseEntity.ok().body("Comment has been deleted.");
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> changeComment(@PathVariable Long id, @RequestBody String text){
        Comment commentObject = commentManager.findById(id).get();
        commentObject.setText(text);
        commentManager.save(commentObject);
        return ResponseEntity.ok().body("Comment has been changed.");
    }
}
