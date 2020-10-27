package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.UserRepo;
import com.themdtnoauthorization.noauthorization.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserManager {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserManager(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User save(User user){
        return userRepo.save(user);
    }

    public ResponseEntity<String> createNewUser(User user) {
        if (!userRepo.findUserByEmail(user.getEmail()).isPresent()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername(user.getEmail());
//            user.setPassword(user.getPassword());
            userRepo.save(user);
            return ResponseEntity.ok().body("User has been created.");
        } else return ResponseEntity.badRequest().body("User with this email already exists.");
    }

    public User findById(Long id) {
         return userRepo.findById(id).orElseThrow(()-> new RuntimeException("No user found."));
    }

    public Optional<User> findByEmail(String email) { return userRepo.findUserByEmail(email);}

    public Optional<User> findUserByUsername(String username) { return userRepo.findUserByUsername(username);}

    public Collection<User> findAll(){
        return userRepo.findAll();
    }

    public void deleteUserById(Long id){
        userRepo.delete(findById(id));
    };
}
