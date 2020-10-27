package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.entity.User;
import com.themdtnoauthorization.noauthorization.model.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserManager userManager;

    private PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userManager.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found.")); //zmieniłem email
        final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
        logger.info(">>>>>>> user email = "+user.getEmail() + " , username = "+user.getUsername() + " , password = " +user.getPassword()+ " , role = "+user.getRole());
        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();

        roleList.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));

//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roleList);
        return new CustomUser(user.getUsername(), user.getPassword(), roleList, user.getId());

    }
}