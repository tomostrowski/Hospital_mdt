package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginApi {

    @PostMapping
    public String login(@RequestBody User user){

        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTimeMillis+20000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
    }
}
