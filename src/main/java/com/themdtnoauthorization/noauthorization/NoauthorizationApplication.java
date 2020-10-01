package com.themdtnoauthorization.noauthorization;

import com.themdtnoauthorization.noauthorization.manager.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
//@ComponentScan({"com.themdtnoauthorization.noauthorization.dao"})
public class NoauthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoauthorizationApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/hello/*"));
        return filterRegistrationBean;
    }
}
