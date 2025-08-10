package com.lcdev25.authnservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.lcdev25.idp.IdentityPlatformApplication.class, args);
    }

}
