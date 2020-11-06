package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.ConfirmationTokenRepo;
import com.themdtnoauthorization.noauthorization.dao.UserRepo;
import com.themdtnoauthorization.noauthorization.entity.ConfirmationToken;
import com.themdtnoauthorization.noauthorization.entity.User;
import com.themdtnoauthorization.noauthorization.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class UserManager {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
    private final ConfirmationTokenRepo confirmationTokenRepo;

    public UserManager(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService, ConfirmationTokenRepo confirmationTokenRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
        this.confirmationTokenRepo = confirmationTokenRepo;
    }

    public User save(User user){
        return userRepo.save(user);
    }

    public ResponseEntity<String> createNewUser(HttpServletRequest request, User user) {
        String appUrl =
                "http://" + request.getServerName() +
                        ":" + request.getServerPort() +
                        "/api/user/confirm?token=";

        if (!userRepo.findUserByEmail(user.getEmail()).isPresent()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername(user.getEmail());
//            user.setPassword(user.getPassword());
            userRepo.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepo.save(confirmationToken);
            String link = appUrl+confirmationToken.getConfirmationToken();
            try{
                emailService.sendConfirmationEmail(user, link);
            } catch ( MailException e) {
                // catch error
                log.info("Error sending email:  "+e.getMessage());
            }

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

    public void confirmAccount(String token) {

        ConfirmationToken confirmationToken = confirmationTokenRepo.findByConfirmationToken(token).orElseThrow(() -> new RuntimeException("No account confirmation token"));
        User user = userRepo.findUserByEmail(confirmationToken.getUser().getEmail()).orElseThrow(() -> new RuntimeException("Could not find user by token/email"));
        user.setEnabled(true);
        userRepo.save(user);
    }

    public void resetPassword(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found by email"));
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        String password = confirmationToken.getConfirmationToken().substring(0,8);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(user);
        try{
            emailService.sendNewPasswordEmail(user, password);
        } catch ( MailException e) {
            // catch error
            log.info("Error sending email:  "+e.getMessage());
        }

    }

    public UserModel getloggedUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setEnabled(user.isEnabled());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setUsername(user.getUsername());
        userModel.setName(user.getName());
        userModel.setRole(user.getRole());
       return userModel;
    }
}
