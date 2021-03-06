package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.Comment;
import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.entity.User;
import com.themdtnoauthorization.noauthorization.manager.CommentManager;
import com.themdtnoauthorization.noauthorization.manager.MedicalProfessionalManager;
import com.themdtnoauthorization.noauthorization.manager.UserManager;
import com.themdtnoauthorization.noauthorization.model.CommentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class CommentApi {
    private final CommentManager commentManager;
    private final UserManager userManager;

    public CommentApi(CommentManager commentManager, UserManager userManager) {
        this.commentManager = commentManager;
        this.userManager = userManager;
    }

    @GetMapping("/{id}")
    public Optional<Comment> findById(@PathVariable Long id){
        return  commentManager.findById(id);
    }

    @GetMapping("/all")
    public Iterable<Comment> findAll(){
        return commentManager.findAll();
    }

    @GetMapping("/list")
    public Iterable<CommentModel> list(){
        return commentManager.list();
    }

    @PostMapping("/new")
    public Long addNew(@RequestBody Comment comment){
        return commentManager.create(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        commentManager.deleteById(id);
        return ResponseEntity.ok().body("Comment has been deleted.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeComment(@PathVariable Long id, @RequestBody String text){
        Comment commentObject = commentManager.findById(id).get();
        commentObject.setText(text);
        commentManager.save(commentObject);
        return ResponseEntity.ok().body("Comment has been changed.");
    }

    @PatchMapping("/{id}/author={authorId}")
    public ResponseEntity<String> setAuthor(@PathVariable Long id, @PathVariable Long authorId){
        Comment comment = commentManager.findById(id).orElseThrow(()-> new RuntimeException("Comment does not exist."));
        User author = userManager.findById(authorId);
        comment.setAuthor(author);
        commentManager.save(comment);
        return ResponseEntity.ok().body("Author has been added to comment.");
    }

    @PatchMapping("/{id}/text")
    public ResponseEntity<String> changeCommentText(@PathVariable Long id, @RequestBody String text){
        commentManager.changeCommentText(id, text);
        return ResponseEntity.ok().header("Comment edited").body("Comment has been edited.");
    }
}
