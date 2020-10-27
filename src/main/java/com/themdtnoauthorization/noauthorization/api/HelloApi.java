package com.themdtnoauthorization.noauthorization.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@CrossOrigin
public class HelloApi {

    @GetMapping("/test0")
    public String hello(){
        return "Cześć wszystkim niezalogowanym! :)";
    }

    @GetMapping("/test1")
    public String helloLoggedin(){
        return "Cześć wszystkim zalogowanym! :)";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/test2")
    public String helloforUsers(){
        return "Cześć tu wchodzą wszyscy zalogowani, z rolą USER )";
    }

    @PreAuthorize("hasRole('USER')"+ "|| hasRole('ADMIN')")
    @GetMapping("/test3")
    public String helloforLoggedUsers(){
        return "Cześć tu wchodzą wszyscy zalogowani, z rolą USER lub ADMIN. )";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test4")
    public String helloForAdminsOnly(){
        return "Strefa tylko dla admina";
    }

}
