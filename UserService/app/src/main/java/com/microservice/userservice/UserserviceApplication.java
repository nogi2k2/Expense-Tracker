package com.microservice.userservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserserviceApplication {
    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(UserserviceApplication.class, args);
    }
}
