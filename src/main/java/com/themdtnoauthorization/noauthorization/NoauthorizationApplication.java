package com.themdtnoauthorization.noauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@ComponentScan({"com.themdtnoauthorization.noauthorization.dao"})
public class NoauthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoauthorizationApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
