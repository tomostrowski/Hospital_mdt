package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.entity.User;
import com.themdtnoauthorization.noauthorization.manager.MedicalProfessionalManager;
import com.themdtnoauthorization.noauthorization.manager.UserManager;
import com.themdtnoauthorization.noauthorization.model.CustomUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserApi {
    private final UserManager userManager;
    private final MedicalProfessionalManager medicalProfessionalManager;

    public UserApi(UserManager userManager, MedicalProfessionalManager medicalProfessionalManager) {
        this.userManager = userManager;
        this.medicalProfessionalManager = medicalProfessionalManager;
    }

    @PostMapping("/new")
    public ResponseEntity<String> addNewUser(HttpServletRequest request, @RequestBody User user) { return userManager.createNewUser(request, user); }

    @GetMapping("/findByEmail={email}")
    public User findByEmail(@PathVariable String email){
        return userManager.findByEmail(email).orElseThrow(()->new RuntimeException("No user with this email."));
    }

    @GetMapping("/loggedUserInfo")
    public User loggedUserInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userManager.findUserByUsername(auth.getName()).orElseThrow(()->new RuntimeException("No user found."));
    }

    @GetMapping("/resetPassword")
    public ResponseEntity<String> remindPassword(@RequestParam String email){
        userManager.resetPassword(email);
        return ResponseEntity.ok().body("Your account has been activated. Please visit the MDT page.");
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmAccount(@RequestParam String token){
        userManager.confirmAccount(token);
        return ResponseEntity.ok().body("Your account has been activated. Please log in to the MDT page.");
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all")
    public Collection<User> findAll(){
        return userManager.findAll();
    }

    @PatchMapping("/{username}/role={role}")
    public ResponseEntity<String> setRole(@PathVariable String username, @PathVariable String role){
        User user = userManager.findUserByUsername(username).orElseThrow(()-> new RuntimeException("User not found."));
        user.setRole(role);
        userManager.save(user);
        return ResponseEntity.ok().body("Role of user "+user.getUsername()+ " has been changed to "+role);
    }

    @PatchMapping("/{id}/role={role}")
    public ResponseEntity<String> setRole(@PathVariable Long id, @PathVariable String role){
        User user = userManager.findById(id);
        user.setRole(role);
        userManager.save(user);
        return ResponseEntity.ok().body("Role of user "+user.getFirstName()+" "+user.getLastName()+" email:"+user.getUsername()+ " has been changed to "+role);
    }

    @PatchMapping("/activate")
    public ResponseEntity<String> setEnabledTrue(@RequestParam String email){
        User user = userManager.findByEmail(email).orElseThrow(()-> new RuntimeException("There is no user registered with this email"));
        user.setEnabled(true);
        userManager.save(user);
        return ResponseEntity.ok().body("Account of user "+user.getFirstName()+" "+user.getLastName()+" has been activated");
    }


    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    @PatchMapping("/{id}/setFirstName={firstName}")
    public ResponseEntity<String> setFirstName(@PathVariable Long id, @PathVariable String firstName){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userManager.findById(id);
        user.setFirstName(firstName);
        userManager.save(user);
     return ResponseEntity.ok().body("First name has been set to "+ firstName+" >> Zalogowany jesteś jako "+auth.getName());
    }

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    @PatchMapping("/{id}/setLastName={lastName}")
    public ResponseEntity<String> setLastName(@PathVariable Long id, @PathVariable String lastName){
        User user = userManager.findById(id);
        user.setLastName(lastName);
        userManager.save(user);
        return ResponseEntity.ok().body("Last name has been set to "+ lastName);
    }

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    @PatchMapping("/{id}/setEmail={email}")
    public ResponseEntity<String> setEmailAndUsername(@PathVariable Long id, @PathVariable String email){
        User user = userManager.findById(id);
        user.setEmail(email);
        user.setUsername(email);
        userManager.save(user);
        return ResponseEntity.ok().body("Email has been set to "+ email);
    }

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    @PatchMapping("/{id}/setMedic={medicId}")
    public ResponseEntity<String> setMedic(@PathVariable Long id, @PathVariable Long medicId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userManager.findById(id);
        MedicalProfessional medicalProfessional = medicalProfessionalManager.findById(medicId).orElseThrow(()-> new RuntimeException("Medic not found."));
        user.setMedicalProfessional(medicalProfessional);
        userManager.save(user);
        return ResponseEntity.ok().body("Medic "+medicalProfessional.getName()+" has been connected to user "+user.getEmail() +" >> Zalogowany jesteś jako "+auth.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userManager.deleteUserById(id);
    }
}
